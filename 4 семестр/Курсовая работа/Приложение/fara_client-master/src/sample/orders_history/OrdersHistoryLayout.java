package sample.orders_history;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.MainApp;
import sample.entity.Order;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class OrdersHistoryLayout {

    private MainApp mainApp;
    private BorderPane rootLayout;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setRootLayout(BorderPane rootLayout) {
        this.rootLayout = rootLayout;
    }

    @FXML
    private TableView<Order> orderTableView;

    @FXML
    private TableColumn<Order, String> id;
    @FXML
    private TableColumn<Order, String> dateTime;

    @FXML
    public void initialize() {
        orderTableView.setPlaceholder(new Label("Ничего не найдено!"));
        orderTableView.setItems(getOrders());

        id.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getId().toString()));
        dateTime.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getOrderTime()));
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
            List<Order> sortedList = new ArrayList<>();
            for (Order order : responseList) {
                if (order.getStatus() == 3) {
                    sortedList.add(order);
                }
            }

            return FXCollections.observableArrayList(sortedList);

        } catch (Exception e) {
            return FXCollections.observableArrayList();
        }
    }

    @FXML
    public void handleCheckAction() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("check_dialog.fxml"));
        AnchorPane page = loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Чек №" + orderTableView.getSelectionModel().getSelectedItem().getId() + " от " + orderTableView.getSelectionModel().getSelectedItem().getOrderTime());
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(mainApp.getPrimaryStage());

        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        CheckDialog controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setCounts(orderTableView.getSelectionModel().getSelectedItem().getCounts());
        controller.setDishes(orderTableView.getSelectionModel().getSelectedItem().getDishes());
        dialogStage.showAndWait();
    }
}
