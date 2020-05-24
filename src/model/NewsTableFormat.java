package model;

import controller.EmployeeController;
import exception.ConnectionException;
import exception.DataQueryException;
import exception.SQLManageException;

import java.util.GregorianCalendar;

public class NewsTableFormat {
    private Integer id;
    private String title;
    private GregorianCalendar startingDate;
    private GregorianCalendar endDate;
    private String author;

    public NewsTableFormat(Integer id, String title, GregorianCalendar startingDate, GregorianCalendar endDate, String author){
        setId(id);
        setTitle(title);
        setStartingDate(startingDate);
        setEndDate(endDate);
        setAuthor(author);
    }

    public NewsTableFormat(News news){
        this(
                news.getId(),
                news.getTitle(),
                news.getStartingDate(),
                news.getEndDate(),
                getEmployeeName(news.getEmployeeEntityId())
        );
    }

    private static String getEmployeeName(Integer entityId){
        String name = null;
        try {
            EmployeeController employeeController = new EmployeeController();
            name = employeeController.getEmployeeName(entityId);
        } catch (ConnectionException e) {
            e.printStackTrace();
        } catch (DataQueryException e) {
            e.printStackTrace();
        }
        return name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartingDate() {
        return utils.Date.formatTime(startingDate);
    }

    public void setStartingDate(GregorianCalendar startingDate) {
        this.startingDate = startingDate;
    }

    public String getEndDate() {
        return utils.Date.formatTime(endDate);
    }

    public void setEndDate(GregorianCalendar endDate) {
        this.endDate = endDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
