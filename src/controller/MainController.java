package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MainController {

    @FXML
    BorderPane panel;

    // Path to FXML file to display on center
    private static final String pathToHomePanel = "/FXML/homePanel.fxml";
    private static final String pathToCustomersPanel = "/FXML/customersPanel.fxml";
    private static final String pathToOrdersPanel = "/FXML/ordersPanel.fxml";
    private static final String pathToSearchPanel = "/FXML/searchPanel.fxml";



    public void homeBtn(ActionEvent actionEvent) {
        switchCenterPanel(pathToHomePanel);

//        Parent root = null;

//        try {
//            root = FXMLLoader.load(getClass().getResource(pathToHomePanel));
//            panel.setCenter(root);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public void customersBtn(ActionEvent actionEvent) {
        switchCenterPanel(pathToCustomersPanel);
//        VBox root = null;
//
//        try {
//            root = FXMLLoader.load(getClass().getResource(pathToCustomersPanel));
//            panel.setCenter(root);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public void ordersBtn(ActionEvent actionEvent) {
        switchCenterPanel(pathToOrdersPanel);
//        Parent root = null;
//
//        try {
//            root = FXMLLoader.load(getClass().getResource(pathToOrdersPanel));
//            panel.setCenter(root);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    private void switchCenterPanel (String pathToFXML) {
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource(pathToFXML));
            panel.setCenter(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
