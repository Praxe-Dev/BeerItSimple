package view.customer.forms;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import view.View;

import javax.swing.*;

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
