package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginView extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        String pathToFXML = "/FXML/login.fxml";
        Parent root = FXMLLoader.load(getClass().getResource(pathToFXML));

        stage.setTitle("BeerItSimple - Login");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
