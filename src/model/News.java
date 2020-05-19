package model;

import java.util.GregorianCalendar;

public class News {
    private int id;
    private String title;
    private String content;
    private GregorianCalendar startingDate;
    private GregorianCalendar endDate;
    private int employeeEntityId;

    public News(int id, String title, String content, GregorianCalendar startingDate, GregorianCalendar endDate, int employeeEntityId){
        setId(id);
        setTitle(title);
        setContent(content);
        setStartingDate(startingDate);
        setEndDate(endDate);
        setEmployeeEntityId(employeeEntityId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getEmployeeEntityId() {
        return employeeEntityId;
    }

    public void setEmployeeEntityId(int employeeEntityId) {
        this.employeeEntityId = employeeEntityId;
    }

}
