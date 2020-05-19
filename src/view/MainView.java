package view;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import model.News;
import view.news.ThreadNews;
import view.news.Index;

public class MainView extends View{
    private double x = 0, y = 0;
    private News currentShowedNews = null;
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
    @FXML
    Label newsLabel;

    // Path to FXML file to display on center
    private static final String pathToHomePanel = "/FXML/homePanel.fxml";
    private static final String pathToCustomersPanel = "/FXML/customer/index.fxml";
    private static final String pathToOrdersPanel = "/FXML/order/index.fxml";
    private static final String pathToSearchPanel = "/FXML/searchPanel.fxml";

    @Override
    public void init() {
        setNewsTransition();

        ThreadNews threadNews = new ThreadNews(this);
        //threadNews.start();
        new Thread(threadNews).start();

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

        newsLabel.setOnMouseClicked(e -> {
            if(currentShowedNews != null){
                //Open window with news details
                Window newsDetails = new Window("FXML/news/index.fxml", "News details");
                newsDetails.load();
                newsDetails.show();
                Index newsIndex = (Index) newsDetails.getView();
                newsIndex.setNews(currentShowedNews);
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

    public void setNews(News news){
        newsLabel.setText("INFO : " + news.getTitle());
        currentShowedNews = news;
    }

    public void setUsername(String employeeUsername){
        username.setText(employeeUsername);
    }

    private void setNewsTransition(){
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.millis(15000));
        transition.setNode(newsLabel);
        transition.setToX(-1100);
        transition.setAutoReverse(true);
        transition.setCycleCount(TranslateTransition.INDEFINITE);
        transition.play();
    }

}
