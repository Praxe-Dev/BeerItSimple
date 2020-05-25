package view;

import javafx.application.Application;
import javafx.stage.Stage;


public class LoginInterface extends Application {

    public static void main(String[] args) {
        launch(args);
        System.exit(0);
    }

    /**
     * Lance la fenêtre de login au lancement du programme
     */
    @Override
    public void start(Stage stage) {
        Window login = new Window("FXML/loginPanel.fxml", "Login");
        login.load();
        login.show();
    }
}



