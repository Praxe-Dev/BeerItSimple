package view;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class MainView extends View{

    @FXML
    BorderPane mainPanel;
    @FXML
    JFXButton homeBtn;
    @FXML
    JFXButton customersBtn;
    @FXML
    JFXButton ordersBtn;
    @FXML
    JFXButton searchBtn;

    // Path to FXML file to display on center
    private static final String pathToHomePanel = "/FXML/homePanel.fxml";
    private static final String pathToCustomersPanel = "/FXML/customersPanel.fxml";
    private static final String pathToOrdersPanel = "/FXML/order/index.fxml";
    private static final String pathToSearchPanel = "/FXML/searchPanel.fxml";

    @Override
    public void init() {

        homeBtn.setOnAction(e -> {
            setCenter(pathToHomePanel);
        });

        customersBtn.setOnAction(e -> {
            setCenter(pathToCustomersPanel);
        });

        ordersBtn.setOnAction(e -> {
            setCenter(pathToOrdersPanel);
        });

        searchBtn.setOnAction(e -> {
            // TODO: Open new tabPane
        });

        setCenter(pathToHomePanel);
    }

    @Override
    public Pane getRoot() {
        return this.mainPanel;
    }

    /**
     * Get the view and display it on the center Panel
     * @param pathToFxml is the path to the view to display
     */
    public void setCenter(String pathToFxml) {

        Parent center;

        try {
            center = FXMLLoader.load(getClass().getResource(pathToFxml));
            mainPanel.setCenter(center);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
