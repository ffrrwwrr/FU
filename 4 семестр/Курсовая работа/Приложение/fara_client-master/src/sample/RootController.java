package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Menu;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import sample.employees.EmployeeLayout;
import sample.orders.OrdersEditDialog;
import sample.orders.OrdersLayout;
import sample.orders_history.OrdersHistoryLayout;

public class RootController {

    private MainApp mainApp;
    private BorderPane rootLayout;

    void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    void setRootLayout(BorderPane rootLayout) {
        this.rootLayout = rootLayout;
    }

    @FXML
    private Menu menu;

    @FXML
    public void handleOrdersLayoutAction() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("orders/orders_layout.fxml"));
        AnchorPane anchorPane = loader.load();

        rootLayout.setCenter(anchorPane);

        OrdersLayout controller = loader.getController();
        controller.setMainApp(mainApp);
        controller.setRootLayout(rootLayout);
    }

    @FXML
    public void handleOrdersDialogAction() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("orders/orders_edit_dialog.fxml"));
        AnchorPane anchorPane = loader.load();

        rootLayout.setCenter(anchorPane);

        OrdersEditDialog controller = loader.getController();
        controller.setMainApp(mainApp);
        controller.setRootLayout(rootLayout);
    }

    @FXML
    public void handleEmployeesLayoutAction() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("employees/employee_layout.fxml"));
        AnchorPane anchorPane = loader.load();

        rootLayout.setCenter(anchorPane);

        EmployeeLayout controller = loader.getController();
        controller.setMainApp(mainApp);
        controller.setRootLayout(rootLayout);
    }

    @FXML
    public void handleOrdersHistoryLayoutAction() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("orders_history/orders_history_layout.fxml"));
        AnchorPane anchorPane = loader.load();

        rootLayout.setCenter(anchorPane);

        OrdersHistoryLayout controller = loader.getController();
        controller.setMainApp(mainApp);
        controller.setRootLayout(rootLayout);
    }

    @FXML
    public void handleAboutAuthorLayoutAction() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("about/About_author.fxml"));
        AnchorPane anchorPane = loader.load();

        rootLayout.setCenter(anchorPane);
    }

    @FXML
    public void handleAboutProgramLayoutAction() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("about/About_program.fxml"));
        AnchorPane anchorPane = loader.load();

        rootLayout.setCenter(anchorPane);
    }
}
