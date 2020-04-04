package controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    private JFXButton customerButton;
    @FXML
    private JFXButton orderButton;
    @FXML
    private JFXButton productButton;
    @FXML
    private JFXButton searchButton;

    public void onClickSearchButton() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // TODO
    }
}
