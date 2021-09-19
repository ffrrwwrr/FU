package sample.employees;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.MainApp;
import sample.entity.Employee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class EmployeeLayout {

    private MainApp mainApp;
    private BorderPane rootLayout;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setRootLayout(BorderPane rootLayout) {
        this.rootLayout = rootLayout;
    }

    @FXML
    private TableView<Employee> employeeTableView;
    @FXML
    private TableColumn<Employee, String> name;
    @FXML
    private TableColumn<Employee, String> position;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label positionLabel;

    @FXML
    public void initialize() {
        employeeTableView.setPlaceholder(new Label("Ничего не найдено!"));
        employeeTableView.setItems(getEmployees());
        employeeTableView.getSelectionModel().selectedItemProperty().addListener(
                ((observableValue, oldValue, newValue) -> showDetails(newValue)));

        name.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getLastName() + " " + c.getValue().getFirstName()));
        position.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPosition().getPosition()));
    }

    public void showDetails(Employee employee) {
        if (employee != null) {
            id.setText(employee.getId().toString());
            phone.setText(employee.getPhoneNumber());
            positionLabel.setText(employee.getPosition().getPosition());
        }
    }

    public ObservableList<Employee> getEmployees() {
        try {
            URL url = new URL("http://localhost:8080/employees");
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
            List<Employee> responseList = new Gson().fromJson(stringResponse, new TypeToken<List<Employee>>() {
            }.getType());
            return FXCollections.observableArrayList(responseList);

        } catch (Exception e) {
            return FXCollections.observableArrayList();
        }
    }

    @FXML
    public void handleNewAction() {
        Employee employee = new Employee();
        boolean isOkClicked = showEditDialog(employee);
        if (isOkClicked) {
            newEmployee(employee);
            employeeTableView.setItems(getEmployees());
        }
    }

    private boolean showEditDialog(Employee employee) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("employee_edit_dialog.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Редактирование сотрудника");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainApp.getPrimaryStage());

            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            EmployeeEditDialog controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setEmployee(employee);
            dialogStage.showAndWait();

            return controller.isOkClicked();

        } catch (IOException e) {
            return false;
        }
    }

    @FXML
    public void handleEditAction() {
        int selectedIndex = employeeTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Employee employee = employeeTableView.getItems().get(selectedIndex);

            boolean isOkClicked = showEditDialog(employee);
            if (isOkClicked) {
                employeeTableView.getItems().set(selectedIndex, employee);
                showDetails(employee);
                putEmployee(employee, employee.getId());
            }

        } else showAlert();
    }

    @FXML
    public void handleDeleteAction() {
        int selectedIndex = employeeTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Long id = employeeTableView.getItems().get(selectedIndex).getId();
            employeeTableView.getItems().remove(selectedIndex);
            deleteEmployee(id);

        } else {
            showAlert();
        }
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("Ошибка");
        alert.setHeaderText("Выберите сотрудника");
        alert.showAndWait();
    }

    private void deleteEmployee(Long id) {
        try {
            URL url = new URL("http://localhost:8080/employees/" + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            connection.getInputStream();

        } catch (Exception e) {
            System.out.println("Ошибка");
        }
    }

    public void putEmployee(Employee employee, Long id) {
        try {
            URL url = new URL("http://localhost:8080/employees/" + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            Gson gson = new Gson();
            String json = gson.toJson(employee);
            OutputStream os = connection.getOutputStream();
            byte[] input = json.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
            os.flush();

            connection.getInputStream();

        } catch (Exception e) {
            System.out.println("Ошибка");
        }
    }

    public void newEmployee(Employee employee) {
        try {
            URL url = new URL("http://localhost:8080/employees");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            Gson gson = new Gson();
            String json = gson.toJson(employee);
            OutputStream os = connection.getOutputStream();
            byte[] input = json.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
            os.flush();

            connection.getInputStream();

        } catch (Exception e) {
            System.out.println("Ошибка");
        }
    }
}
