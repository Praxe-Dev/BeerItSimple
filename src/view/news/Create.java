package view.news;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import controller.NewsController;
import exception.SQLManageException;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import model.News;
import view.PopUp;
import view.View;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.GregorianCalendar;

public class Create extends View {
    @FXML
    private JFXTextField title;
    @FXML
    private DatePicker startingDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private JFXTimePicker startingTime;
    @FXML
    private JFXTimePicker endTime;
    @FXML
    private JFXTextArea contentArea;
    @FXML
    private JFXButton closeBtn;
    @FXML
    private JFXButton submitBtn;

    @Override
    public void init() {
        startingTime.set24HourView(true);
        endTime.set24HourView(true);

        closeBtn.setOnAction(e -> {
            this.closeWindow();
        });

        submitBtn.setOnAction(e -> {
            if(validateInfo()){
                try {
                    insertNews();
                    PopUp.showSuccess("News added", "Your news has been successfully added");
                    this.closeWindow();
                } catch(SQLManageException err){
                    err.showMessage();
                }
            }
        });
    }

    private boolean validateInfo(){
        return true;
    }

    public void insertNews() throws SQLManageException {
        NewsController newsController = new NewsController();
        LocalDate start = startingDate.getValue();
        LocalDate end = endDate.getValue();
        LocalTime startT = startingTime.getValue();
        LocalTime endT = endTime.getValue();
        GregorianCalendar startGC = new GregorianCalendar(start.getYear(), start.getMonthValue(), start.getDayOfMonth(), startT.getHour(), startT.getMinute(), startT.getSecond());
        GregorianCalendar endGC = new GregorianCalendar(end.getYear(), end.getMonthValue(), end.getDayOfMonth(), endT.getHour(), endT.getMinute(), endT.getSecond());
        News news = new News(title.getText(), contentArea.getText(), startGC, endGC, 2);
        System.out.println(utils.Date.formatTime(news.getStartingDate()));
        try {
            newsController.insertNews(news);
        } catch(SQLManageException e){
            throw e;
        }
    }
}
