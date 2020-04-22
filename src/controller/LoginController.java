package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dataAccess.EmployeeDataTest;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class LoginController {

    @FXML
    private Label labelTest;
    @FXML
    private VBox loginContainer;
    @FXML
    private JFXTextField employeeMatricule;
    @FXML
    private JFXPasswordField employeePassword;

    @FXML
    public void openMenu() {
        boolean log = true;

        try {
            testSql();
        } catch (Exception e) {
            System.out.println("TEST");
            employeePassword.setStyle("-fx-background-color: rgba(255,0,0,0.5)");
            log = false;
        }

        if (log) {

            // Get the login stage to close it.
            Stage loginStage = (Stage) loginContainer.getScene().getWindow();
            loginStage.hide();

            BorderPane root = null;
            VBox home;
            try {
                String fxmlPath = "/FXML/MainPanel.fxml";
                root = FXMLLoader.load(getClass().getResource(fxmlPath));

                Stage stage = new Stage();
                stage.setTitle("BeerItSimple - Menu");
                stage.setScene(new Scene(root));
                stage.setMinHeight(500.0);
                stage.setMinWidth(1100.0);

                home = FXMLLoader.load(getClass().getResource("/FXML/homePanel.fxml"));
                root.setCenter(home);

                stage.show();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public void testSql() {
        EmployeeDataTest.login(Integer.parseInt(employeeMatricule.getText()), employeePassword.getText());
    }
}
