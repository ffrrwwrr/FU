package sample.orders;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import sample.MainApp;
import sample.entity.Dish;
import sample.entity.Order;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class OrdersLayout {

    private MainApp mainApp;
    private BorderPane rootLayout;

    private ObservableList<Order> orders;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setRootLayout(BorderPane rootLayout) {
        this.rootLayout = rootLayout;
    }

    @FXML
    private AnchorPane anchor;

    @FXML
    private ScrollPane pane;

    @FXML
    private VBox box;

    @FXML
    public void initialize() {
        orders = getOrders();

        for (Order order : orders) {
            HashMap<Long, Long> counts = order.getCounts();
            if (order.getStatus() < 3) {
                VBox element = new VBox();
                element.setStyle("-fx-border-color:black;");
                switch (order.getStatus()) {
                    case 0:
                        element.setStyle("-fx-background-color:white;");
                        break;

                    case 1:
                        element.setStyle("-fx-background-color:yellow;");
                        break;

                    case 2:
                        element.setStyle("-fx-background-color:green;");
                        break;
                }

                element.setId(order.getId().toString());
                element.setOnMouseClicked(mouseEvent -> {
                    order.setStatus(order.getStatus() + 1);
                    putOrder(order, order.getId());
                    switch (order.getStatus()) {
                        case 1:
                            element.setStyle("-fx-background-color:yellow;");
                            break;

                        case 2:
                            element.setStyle("-fx-background-color:green;");
                            break;

                        default:
                            box.getChildren().remove(element);
                            break;
                    }
                });

                Label number = new Label("№" + order.getId());
                number.setFont(Font.font("Cambria", 21));
                number.setPadding(new Insets(10, 10, 10, 10));

                Label locator = new Label("Локатор: " + order.getLocator());
                locator.setPadding(new Insets(0, 0, 10, 10));
                element.getChildren().addAll(number, locator);

                Set<Dish> dishes = order.getDishes();
                int i = 1;
                for (Dish dish : dishes) {
                    Label text = new Label(i + ". " + dish.getName() + " " + counts.get(dish.getId()));
                    text.setPadding(new Insets(0, 0, 10, 10));
                    element.getChildren().add(text);
                    i++;
                }

                box.getChildren().add(element);
            }
        }
    }

    public ObservableList<Order> getOrders() {
        try {
            URL url = new URL("http://localhost:8080/orders/");
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
            List<Order> responseList = new Gson().fromJson(stringResponse, new TypeToken<List<Order>>() {
            }.getType());
            return FXCollections.observableArrayList(responseList);

        } catch (Exception e) {
            return FXCollections.observableArrayList();
        }
    }

    public void putOrder(Order order, Long id) {
        try {
            URL url = new URL("http://localhost:8080/orders/" + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
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
}