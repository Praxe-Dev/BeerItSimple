package view.news;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import controller.NewsController;
import exception.ConnectionException;
import exception.SQLManageException;
import exception.UpdateException;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import model.News;
import utils.Validators;
import utils.PopUp;
import view.View;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Update extends View {
    @FXML
    private JFXTextField title;
    @FXML
    private DatePicker startingDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private JFXTextArea contentArea;
    @FXML
    private JFXButton closeBtn;
    @FXML
    private JFXButton submitBtn;

    private News news = null;

    @Override
    public void init() {
        if(news != null) {
            title.setText(news.getTitle());
            contentArea.setText(news.getContent());

            initDatePicker();

            Validators.setReqField(title);
            Validators.setReqField(contentArea);

            closeBtn.setOnAction(e -> {
                this.closeWindow();
            });

            submitBtn.setOnAction(e -> {
                if (validateInfo()) {
                    try {
                        updateNews();
                        PopUp.showSuccess("News updated", "Your news has been successfully updated");
                        this.closeWindow();
                    } catch (SQLManageException err) {
                        err.showMessage();
                    }
                }
            });
        } else {
            this.closeWindow();
        }
    }

    private boolean validateInfo(){
        LocalDate start = startingDate.getValue();
        LocalDate end = endDate.getValue();
        if(Validators.validate(title) && Validators.validate(contentArea) && contentArea.validate()){
            if(startingDate.getValue() == null){
                PopUp.showError("Date error", "Please choose start date.");
                return false;
            }

            if(endDate.getValue() == null){
                PopUp.showError("Date error", "Please choose end date.");
                return false;
            }

            if(Validators.validateBetweenDates(end, start)){
                PopUp.showError("Date error", "End date must be later than the start date.");
                return false;
            }
            return true;
        }
        return false;
    }

    public void updateNews() throws SQLManageException {
        NewsController newsController = null;
        try {
            newsController = new NewsController();
        } catch (ConnectionException e) {
            showError(e.getTypeError(), e.getMessage());
        }
        LocalDate start = startingDate.getValue();
        LocalDate end = endDate.getValue();
        GregorianCalendar startGC = new GregorianCalendar(start.getYear(), start.getMonthValue()-1, start.getDayOfMonth());
        GregorianCalendar endGC = new GregorianCalendar(end.getYear(), end.getMonthValue()-1, end.getDayOfMonth());
        News updateNews = new News(news.getId(), title.getText(), contentArea.getText(), startGC, endGC, 2);
        try {
            newsController.updateNews(updateNews);
        } catch(UpdateException e){
            showError(e.getTypeError(), e.getMessage());
        }
    }

    public void setNews(News news){
        this.news = news;
    }

    private void initDatePicker(){
        //Starting
        GregorianCalendar startingDateGC = news.getStartingDate();
        Calendar calendar = startingDateGC;
        startingDate.setValue(LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DAY_OF_MONTH)));
        
        //Ending
        GregorianCalendar endingDateGC = news.getEndDate();
        Calendar calendarEnd = endingDateGC;
        endDate.setValue(LocalDate.of(calendarEnd.get(Calendar.YEAR), calendarEnd.get(Calendar.MONTH)+1, calendarEnd.get(Calendar.DAY_OF_MONTH)));
    }
}
