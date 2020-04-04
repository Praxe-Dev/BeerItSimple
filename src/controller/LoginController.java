package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable  {
//    private Label labelTest;

    @FXML
    private Label labelTest;
    @FXML
    private VBox loginContainer;

    @FXML
    public void openMenu() {

        // Get the login stage to close it.
        Stage loginStage = (Stage) loginContainer.getScene().getWindow();
        loginStage.hide();

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/FXML/templateTest.fxml"));

            Stage stage = new Stage();
            stage.setTitle("BeerItSimple - Menu");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // TODO

    }
}
