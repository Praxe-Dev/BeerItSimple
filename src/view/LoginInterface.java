package view;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Locale;

public class LoginInterface extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Lance la fenêtre de login au lancement du programme
     */
    @Override
    public void start(Stage stage) {
        Window login = new Window("FXML/loginPanel.fxml", "Login");
//        Locale.setDefault(Locale.ENGLISH);
        login.load();
        login.show();
    }
}



