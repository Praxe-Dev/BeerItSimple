package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class templateTestController {
    @FXML
    VBox vTest;

    public void testBtn(ActionEvent actionEvent) {
        Parent root = null;
        try {
        root = FXMLLoader.load(getClass().getResource("/FXML/testMenu.fxml"));
        vTest.getChildren().clear();
        vTest.getChildren().add(root);
        System.out.println("TEST");
//        vTest.autosize();

        } catch (Exception e) {
            e.getMessage();
        }

    }


}
