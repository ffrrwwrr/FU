package sample.orders_history;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import sample.entity.Dish;

import java.util.HashMap;
import java.util.Set;

public class CheckDialog {

    private Stage dialogStage;
    private Set<Dish> dishes;
    private HashMap<Long, Long> counts;

    @FXML
    private TableView<Dish> dishTableView;
    @FXML
    private TableColumn<Dish, String> dish;
    @FXML
    private TableColumn<Dish, String> price;
    @FXML
    private TableColumn<Dish, String> count;
    @FXML
    private Label sumLabel;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setCounts(HashMap<Long, Long> counts) {
        this.counts = counts;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;

        double sum = 0;

        for (Dish dish : dishes) {
            sum += dish.getPrice() * counts.get(dish.getId());
        }

        sumLabel.setText("Общая сумма: " + sum + " руб.");

        dishTableView.setPlaceholder(new Label("Ничего не найдено!"));
        dishTableView.setItems(FXCollections.observableArrayList(dishes));
        dish.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getName()));
        price.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getPrice() + " руб."));
        count.setCellValueFactory(p -> new SimpleStringProperty(counts.get(p.getValue().getId()).toString()));
    }

    @FXML
    public void handleCancelAction() {
        dialogStage.close();
    }
}
