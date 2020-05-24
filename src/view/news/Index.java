package view.news;

import com.jfoenix.controls.JFXButton;
import controller.NewsController;
import exception.DeletionExceiption;
import exception.NoRowSelected;
import exception.SQLManageException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.News;
import model.NewsTableFormat;
import view.PopUp;
import view.View;
import view.Window;
import view.news.Update;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Index extends View implements Initializable {
    @FXML
    private JFXButton addNews;
    @FXML
    private JFXButton editNews;
    @FXML
    private JFXButton detailsNews;
    @FXML
    private JFXButton deleteNews;
    @FXML
    private TableView<NewsTableFormat> newsTable;
    @FXML
    private TableColumn<NewsTableFormat, Integer> id;
    @FXML
    private TableColumn<NewsTableFormat, String> title;
    @FXML
    private TableColumn<NewsTableFormat, String> startingDate;
    @FXML
    private TableColumn<NewsTableFormat, String> endDate;
    @FXML
    private TableColumn<NewsTableFormat, String> author;
    @FXML
    private JFXButton refreshBtn;

    private NewsController newsController;

    public Index(){
        newsController = new NewsController();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
        initTableNews();
    }

    @Override
    public void init() {

        refreshBtn.setOnAction(e -> {
            updateTable();
        });

        addNews.setOnAction(e -> {
            Window createNews = new Window("FXML/news/create.fxml", "BeerItSimple - Add news");
            createNews.resizable(false);
            createNews.load();
            createNews.show();
        });

        detailsNews.setOnAction(e -> {
            Window viewNews = new Window("FXML/news/read.fxml", "BeerItSimple - News details");
            viewNews.resizable(false);
            viewNews.load();
            Read newsRead = (Read) viewNews.getView();
            News selectedNews = getSelectedNews();
            if(selectedNews != null) {
                newsRead.setNews(selectedNews);
                viewNews.show();
            } else {
                viewNews.close();
            }
        });

        editNews.setOnAction(e -> {
            Window updateNews = new Window("FXML/news/update.fxml", "BeerItSimple - Update news");
            updateNews.resizable(false);
            updateNews.load();
            News news = getSelectedNews();
            Update updateView = (Update) updateNews.getView();
            if(news != null){
                updateView.setNews(news);
                updateNews.show();
            } else {
                updateNews.close();
            }

        });

        deleteNews.setOnAction(e -> {
            try {
                News news = getSelectedNews();
                if(PopUp.showConfirm("Confirm delete", "Are you sur you want to delete the news [(" + news.getId() + ") " + news.getTitle() + "] ?")) {
                    if (newsController.deleteNews(news)) {
                        updateTable();
                    }
                }
            } catch (DeletionExceiption ex) {
                ex.showError();
            } catch (NullPointerException ex) {
                new NoRowSelected();
            }
        });
    }

    public void initTableNews() {
        id.setCellValueFactory(new PropertyValueFactory<NewsTableFormat, Integer>("id"));
        title.setCellValueFactory(new PropertyValueFactory<NewsTableFormat, String>("title"));
        startingDate.setCellValueFactory(new PropertyValueFactory<NewsTableFormat, String>("startingDate"));
        endDate.setCellValueFactory(new PropertyValueFactory<NewsTableFormat, String>("endDate"));
        author.setCellValueFactory(new PropertyValueFactory<NewsTableFormat, String>("author"));

        // Transforme les orders en OrderTableFormat pour l'affichage
        ArrayList<News> newsList = new ArrayList<News>();
        try{
            newsList = newsController.getAllNews();
        } catch(SQLManageException ex){
           ex.showMessage();
        }
        ArrayList<NewsTableFormat> newsRow = new ArrayList<>();
        for (News news : newsList) {
            newsRow.add(new NewsTableFormat(news));
        }

        newsTable.getItems().setAll(newsRow);

        // Permet de redimensionner les colonnes lorsque la taille de la fenêtre change
        newsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        for (int i = 0; i < newsTable.getColumns().size(); i++) {
            newsTable.getColumns().get(i).prefWidthProperty().bind(newsTable.widthProperty().multiply((double) 1 / newsTable.getColumns().size()));
        }
    }

    private News getSelectedNews() {
        News news = null;
        try {
            news = newsController.getNewsFromId(newsTable.getSelectionModel().getSelectedItem().getId());
        } catch (Exception e) {
            new NoRowSelected();
        }

        return news;
    }

    private void updateTable() {
        try {
            updateTable(newsController.getAllNews());
        } catch (SQLManageException e) {
            e.showMessage();
        }
    }

    private boolean updateTable(ArrayList<News> newsArrayList) {
        ArrayList<NewsTableFormat> newsTableFormatArrayList = new ArrayList<>();

        for (News news : newsArrayList) {
            newsTableFormatArrayList.add(new NewsTableFormat(news));
        }
        return newsTable.getItems().setAll(newsTableFormatArrayList);
    }
}
