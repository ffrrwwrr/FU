package sample.orders;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import sample.MainApp;
import sample.entity.Dish;
import sample.entity.Order;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class OrdersEditDialog {

    private MainApp mainApp;
    private BorderPane rootLayout;
    private ObservableList<Dish> dishes;
    private HashMap<Long, Long> counts = new HashMap<>();

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setRootLayout(BorderPane rootLayout) {
        this.rootLayout = rootLayout;
    }

    @FXML
    private TableView<Dish> allDishesTable;

    @FXML
    private TableView<Dish> orderedDishesTable;

    @FXML
    private TableColumn<Dish, String> allDishesNameColumn;

    @FXML
    private TableColumn<Dish, String> allDishesPriceColumn;

    @FXML
    private TableColumn<Dish, String> orderedDishesNameColumn;

    @FXML
    private TableColumn<Dish, String> orderedDishesPriceColumn;

    @FXML
    private TableColumn<Dish, String> countColumn;

    @FXML
    private TextField search;

    @FXML
    private Label sumLabel;

    @FXML
    private TextField locator;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    @FXML
    public void initialize() {
        this.dishes = getDishes();
        allDishesTable.setItems(dishes);

        allDishesNameColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getName()));
        allDishesPriceColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getPrice() + " руб."));
        orderedDishesNameColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getName()));
        orderedDishesPriceColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getPrice() + " руб."));
        countColumn.setCellValueFactory(d -> new SimpleStringProperty(counts.get(d.getValue().getId()).toString()));
    }

    public ObservableList<Dish> getDishes() {
        try {
            URL url = new URL("http://localhost:8080/dishes");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();

            String stringResponse = response.toString();
            List<Dish> responseList = new Gson().fromJson(stringResponse, new TypeToken<List<Dish>>() {
            }.getType());
            return FXCollections.observableArrayList(responseList);

        } catch (Exception e) {
            return FXCollections.observableArrayList();
        }
    }

    @FXML
    public void handleClearAction() {
        orderedDishesTable.setItems(FXCollections.observableArrayList());
        calculateSum();
    }

    @FXML
    public void handleCreateAction() throws Exception {
        if (orderedDishesTable.getItems().size() > 0) {
            Order order = new Order();
            order.setStatus(0);
            order.setOrderTime(LocalDateTime.now().format(formatter));
            order.setLocator(locator.getText().toUpperCase());
            order.setCounts(counts);

            Set<Dish> dishHashSet = new HashSet<>(orderedDishesTable.getItems());
            order.setDishes(dishHashSet);

            newOrder(order);
            orderCreated();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("orders_layout.fxml"));
            AnchorPane anchorPane = loader.load();

            rootLayout.setCenter(anchorPane);

            OrdersLayout controller = loader.getController();
            controller.setMainApp(mainApp);
            controller.setRootLayout(rootLayout);
        }
    }

    @FXML
    public void handleMinusAction() {
        int index = orderedDishesTable.getSelectionModel().getSelectedIndex();

        if (index >= 0) {
            Dish dish = orderedDishesTable.getItems().get(index);
            if (counts.get(dish.getId()) > 1) {
                counts.put(dish.getId(), counts.get(dish.getId()) - 1);

            } else {
                orderedDishesTable.getItems().remove(dish);
                counts.remove(dish.getId());
            }

            orderedDishesTable.refresh();
            calculateSum();
        }
    }

    @FXML
    public void handleAddToOrderAction() {
        int index = allDishesTable.getSelectionModel().getSelectedIndex();

        if (index >= 0) {
            Dish dish = allDishesTable.getItems().get(index);
            if (orderedDishesTable.getItems().contains(dish)) {
                counts.put(dish.getId(), counts.get(dish.getId()) + 1);

            } else {
                orderedDishesTable.getItems().add(dish);
                counts.put(dish.getId(), 1L);

            }

            orderedDishesTable.refresh();
            calculateSum();
        }
    }

    @FXML
    public void handleOkAction() {
        ObservableList<Dish> filteredDishes = FXCollections.observableArrayList();
        for (Dish dish : dishes) {
            if (dish.getName().toLowerCase().startsWith(search.getText().toLowerCase())) {
                filteredDishes.add(dish);
            }
        }
        allDishesTable.setItems(filteredDishes);
    }

    private void calculateSum() {
        double sum = 0;

        for (Dish dish : orderedDishesTable.getItems()) {
            sum += dish.getPrice() * counts.get(dish.getId());
        }
        sumLabel.setText("Сумма заказа: " + sum + " руб.");
    }

    public void newOrder(Order order) {
        try {
            URL url = new URL("http://localhost:8080/orders");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            Gson gson = new Gson();
            String json = gson.toJson(order);
            OutputStream os = connection.getOutputStream();
            byte[] input = json.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
            os.flush();

            connection.getInputStream();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ошибка");
        }
    }

    private void orderCreated() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("Информация");
        alert.setHeaderText("Заказ создан!");

        alert.showAndWait();
    }
}
