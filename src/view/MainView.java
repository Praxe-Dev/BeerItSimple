package view;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainView extends View{
    private double x = 0, y = 0;
    @FXML
    Label username;
    @FXML
    HBox topBar;
    @FXML
    JFXButton closeWindow;
    @FXML
    FontAwesomeIcon logout;
    @FXML
    BorderPane mainPanel;
    @FXML
    JFXButton homeBtn;
    @FXML
    JFXButton customersBtn;
    @FXML
    JFXButton ordersBtn;
    @FXML
    JFXButton productBtn;
    @FXML
    JFXButton searchBtn;

    // Path to FXML file to display on center
    private static final String pathToHomePanel = "/FXML/homePanel.fxml";
    private static final String pathToCustomersPanel = "/FXML/customer/index.fxml";
    private static final String pathToOrdersPanel = "/FXML/order/index.fxml";
    private static final String pathToSearchPanel = "/FXML/searchPanel.fxml";

    @Override
    public void init() {
        String windowsUser = System.getProperty("user.name");
        username.setText(windowsUser);
        getStage().setOnHidden(e -> Platform.exit());

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
        productBtn.setOnAction(e -> {
            PopUp.showInfo("WIP", "This section is still work in progress, sorry !");
        });

        searchBtn.setOnAction(e -> {
            Window search = new Window("FXML/search/search.fxml", "BeerItSimple - Search in order");
            search.load();
//            search.getView().setParentView(this);
            search.resizable(false);
            search.show();
        });

        logout.setOnMouseClicked(e -> {
            //TODO: replace with close all windows!
            this.closeWindow();
            Window login = new Window("FXML/loginPanel.fxml", "Login");
            login.load();
            login.show();
        });

        closeWindow.setOnMouseClicked(e -> {
            //TODO: replace with close all windows!
            this.closeWindow();
            for (java.awt.Window window : java.awt.Window.getWindows()) {
                window.dispose();
            }
        });

        setCenter(pathToHomePanel);
        this.window.undecorated();
        makeDraggable(mainPanel);
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

    public void setUsername(String employeeUsername){
        username.setText(employeeUsername);
    }
}
