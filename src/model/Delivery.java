package model;

import java.util.Date;
import java.util.GregorianCalendar;

public class Delivery {
    private Employee employee;
    private Integer id;
    private GregorianCalendar plannedDate;
    private GregorianCalendar deliveredDate;
    private Order order;

    public Delivery(Employee employee, Integer id, GregorianCalendar plannedDate, GregorianCalendar deliveredDate, Order order) {
        setEmployee(employee);
        setId(id);
        setPlannedDate(plannedDate);
        setDeliveredDate(deliveredDate);
        setOrder(order);
    }

    public Delivery(Employee employee, GregorianCalendar plannedDate, Order order) {
        this(employee, null, plannedDate, null, order);
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GregorianCalendar getPlannedDate() {
        return plannedDate;
    }

    public void setPlannedDate(GregorianCalendar plannedDate) {
        this.plannedDate = plannedDate;
    }

    public GregorianCalendar getDeliveredDate() {
        return deliveredDate;
    }

    public void setDeliveredDate(GregorianCalendar deliveredDate) {
        this.deliveredDate = deliveredDate;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
