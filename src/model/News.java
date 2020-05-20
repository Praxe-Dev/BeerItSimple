package model;

import com.mysql.cj.x.protobuf.MysqlxNotice;
import javafx.fxml.Initializable;

import java.util.GregorianCalendar;

public class News {
    private Integer id;
    private String title;
    private String content;
    private GregorianCalendar startingDate;
    private GregorianCalendar endDate;
    private Integer employeeEntityId;

    public News(Integer id, String title, String content, GregorianCalendar startingDate, GregorianCalendar endDate, Integer employeeEntityId){
        setId(id);
        setTitle(title);
        setContent(content);
        setStartingDate(startingDate);
        setEndDate(endDate);
        setEmployeeEntityId(employeeEntityId);
    }

    public News(String title, String content, GregorianCalendar startingDate, GregorianCalendar endDate, Integer employeeEntityId){
        this(null, title, content, startingDate, endDate, employeeEntityId);
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public GregorianCalendar getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(GregorianCalendar startingDate) {
        this.startingDate = startingDate;
    }

    public GregorianCalendar getEndDate() {
        return endDate;
    }

    public void setEndDate(GregorianCalendar endDate) {
        this.endDate = endDate;
    }

    public Integer getEmployeeEntityId() {
        return employeeEntityId;
    }

    public void setEmployeeEntityId(Integer employeeEntityId) {
        this.employeeEntityId = employeeEntityId;
    }

}
