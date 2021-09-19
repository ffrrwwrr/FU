package sample;

import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage primaryStage;

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Restaurant");
//      this.primaryStage.getIcons().add(new Image(MainApp.class.getResourceAsStream("icon.png"))); // - for icon adding
        initRootLayout();
    }

    void initRootLayout() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("RootLayout.fxml"));
        BorderPane rootLayout = loader.load();

        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();

        RootController rootController = loader.getController();
        rootController.setMainApp(this);
        rootController.setRootLayout(rootLayout);
        rootController.handleAboutProgramLayoutAction();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
