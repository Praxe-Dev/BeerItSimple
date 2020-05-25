package view.news;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.News;
import view.View;

public class Read extends View {
    @FXML
    private Label title;
    @FXML
    private Label startingDate;
    @FXML
    private Label endDate;
    @FXML
    private JFXTextArea contentArea;
    @FXML
    private JFXButton closeBtn;

    @Override
    public void init() {
        contentArea.setDisable(true);
        closeBtn.setOnMouseClicked(e -> {
            this.closeWindow();
        });
    }

    public void setNews(News news){
        title.setText(news.getTitle());
        startingDate.setText(utils.Date.formatTime(news.getStartingDate()));
        endDate.setText(utils.Date.formatTime(news.getEndDate()));
        contentArea.setText(news.getContent());
    }
}
