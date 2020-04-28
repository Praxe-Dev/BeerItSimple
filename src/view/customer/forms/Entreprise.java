package view.customer.forms;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import controller.CustomerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.View;

public class Entreprise extends View {

    @FXML
    private Pane mainPane;

    @Override
    public void init() {

    }

    public Pane getRoot() {
        return this.mainPane;
    }
}
