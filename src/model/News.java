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
        this.id = id;
        this.title = title;
        this.content = content;
        this.startingDate = startingDate;
        this.endDate = endDate;
        this.employeeEntityId = employeeEntityId;
    }
}
