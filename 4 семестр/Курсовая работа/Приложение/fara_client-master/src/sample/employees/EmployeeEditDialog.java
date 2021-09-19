package sample.employees;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.entity.Employee;
import sample.entity.Position;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class EmployeeEditDialog {

    private Stage dialogStage;
    private Employee employee;
    private boolean okClicked = false;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;

        positionCheckBox.setItems(getPositions());

        firstName.setText(employee.getFirstName());
        lastName.setText(employee.getLastName());
        phone.setText(employee.getPhoneNumber());
        positionCheckBox.setValue(employee.getPosition());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField phone;
    @FXML
    private ComboBox<Position> positionCheckBox;

    @FXML
    public void handleOkAction() {
        if (firstName.getText() == null || firstName.getText().isEmpty())
            return;

        if (lastName.getText() == null || lastName.getText().isEmpty())
            return;

        if (positionCheckBox.getValue() == null)
            return;

        employee.setFirstName(firstName.getText());
        employee.setLastName(lastName.getText());
        employee.setPhoneNumber(phone.getText());
        employee.setPosition(positionCheckBox.getValue());

        okClicked = true;
        dialogStage.close();

    }

    @FXML
    public void handleCancelAction() {
        dialogStage.close();
    }

    public ObservableList<Position> getPositions() {
        try {
            URL url = new URL("http://localhost:8080/positions");
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
            List<Position> responseList = new Gson().fromJson(stringResponse, new TypeToken<List<Position>>() {
            }.getType());
            return FXCollections.observableArrayList(responseList);

        } catch (Exception e) {
            return FXCollections.observableArrayList();
        }
    }
}
