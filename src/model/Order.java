package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class Order {
    private Customer customer;
    private Integer reference;
    private Boolean isPaid;
    private GregorianCalendar startingDate;
    private Status status;
    private PaymentMethod paymentMethod;
    private ArrayList<OrderLine> orderLineList;
    private Delivery delivery;

    public Order(Customer customer, Integer reference, Boolean isPaid, GregorianCalendar startingDate, Status status, PaymentMethod paymentMethod, Delivery delivery) {
        setCustomer(customer);
        setReference(reference);
        setPaid(isPaid);
        setStartingDate(startingDate);
        setStatus(status);
        setPaymentMethod(paymentMethod);
        orderLineList = new ArrayList<>();
        setDelivery(delivery);
    }

    public Order(Customer customer, Integer reference, Boolean isPaid, GregorianCalendar startingDate, Status status, PaymentMethod paymentMethod){
        this(customer, reference, isPaid, startingDate, status, paymentMethod, null);
    }

    public Order(Customer customer,GregorianCalendar startingDate, PaymentMethod paymentMethod) {
        this(customer, null,false, startingDate, new Status(1), paymentMethod, null);
    }

    public void setOrderLineList(ArrayList<OrderLine> orderLineList) {
        this.orderLineList = orderLineList;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public void addOrderLine(OrderLine orderLine){
        orderLineList.add(orderLine);
    }

    public void setOrderLine(ArrayList<OrderLine> orderLines) {
        orderLineList = orderLines;
    }

    public ArrayList<OrderLine> getOrderLineList(){
        return orderLineList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getReference() {
        return reference;
    }

    public void setReference(Integer reference) {
        this.reference = reference;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    public GregorianCalendar getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(GregorianCalendar startingDate) {
        this.startingDate = startingDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

}
