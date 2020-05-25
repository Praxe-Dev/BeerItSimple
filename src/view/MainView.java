package view;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import model.News;
import model.Role;
import utils.PopUp;
import view.news.ThreadNews;
import view.news.Read;

public class MainView extends View{
    private double x = 0, y = 0;
    private News currentShowedNews = null;
    @FXML
    private Label username;
    @FXML
    private HBox topBar;
    @FXML
    private JFXButton closeWindow;
    @FXML
    private FontAwesomeIcon logout;
    @FXML
    private BorderPane mainPanel;
    @FXML
    private JFXButton homeBtn;
    @FXML
    private JFXButton customersBtn;
    @FXML
    private JFXButton ordersBtn;
    @FXML
    private JFXButton productBtn;
    @FXML
    private JFXButton searchBtn;
    @FXML
    private JFXButton newsBtn;
    @FXML
    private Label newsLabel;
    @FXML
    private VBox boxMenu;

    // Path to FXML file to display on center
    private static final String pathToHomePanel = "/FXML/homePanel.fxml";
    private static final String pathToCustomersPanel = "/FXML/customer/index.fxml";
    private static final String pathToOrdersPanel = "/FXML/order/index.fxml";
    private static final String pathToNewsPanel = "/FXML/news/index.fxml";
    private TranslateTransition transition;

    @Override
    public void init() {
        setNewsTransition();

        ThreadNews threadNews = new ThreadNews(this);
        //threadNews.start();
        new Thread(threadNews).start();

//        getStage().setOnHidden(e -> Platform.exit());

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

        newsBtn.setOnAction(e -> {
            setCenter(pathToNewsPanel);
        });

        logout.setOnMouseClicked(e -> {
            //TODO: replace with close all windows!
            if (PopUp.showConfirm("Sign out", "Are you sure you want to sign out ?")) {
                Window login = new Window("FXML/loginPanel.fxml", "Login");
                switchWindow(login);
            }
        });

        closeWindow.setOnMouseClicked(e -> {
            //TODO: replace with close all windows!
            if (PopUp.showConfirm("Closing", "Are you sure you want to quit the application ?"))
                System.exit(0);
        });

        newsLabel.setOnMouseClicked(e -> {
            if(currentShowedNews != null){
                //Open window with news details
                Window newsDetails = new Window("FXML/news/read.fxml", "News details");
                newsDetails.load();
                newsDetails.show();
                newsDetails.resizable(false);
                Read newsRead = (Read) newsDetails.getView();
                newsRead.setNews(currentShowedNews);
            }
        });

        newsLabel.setOnMouseEntered(e -> {
            transition.pause();
        });

        newsLabel.setOnMouseExited(e -> {
            transition.play();
        });

        setCenter(pathToHomePanel);

        this.window.undecorated();
        makeDraggable(topBar);

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

    public void setRole(Role role){
        if(!role.getName().equals("Manager")){
            boxMenu.getChildren().remove(newsBtn);
        }
        username.setText(username.getText() + " (" + role.getName() + ")");
    }

    private void setNewsTransition(){
        transition = new TranslateTransition();
        transition.setDuration(Duration.millis(10000));
        transition.setNode(newsLabel);
        transition.setToX(-1800);
        transition.setCycleCount(TranslateTransition.INDEFINITE);
        transition.play();
    }
}
