package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UserInterface extends Application {
    private static String pathToLogin = "/FXML/login.fxml";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(pathToLogin));

        stage.setTitle("BeerItSimple - Login");
        stage.setMinHeight(400.0);
        stage.setMinWidth(600.0);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
