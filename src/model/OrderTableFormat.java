package model;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class OrderTableFormat {
    private Integer reference;
    private GregorianCalendar startingDate;
    private String customerName;
    private String paid;
    private String paymentMethod;
    private String status;
    private GregorianCalendar plannedDate;
    private GregorianCalendar deliveredDate;
    private String deliveryMan;
    private static SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");

    public OrderTableFormat(Order order){
        setReference(order.getReference());
        setStartingDate(order.getStartingDate());
        if (order.getCustomer() != null) {
            setCustomerName(order.getCustomer().getEntity().getContactName());
        } else {
            setCustomerName("N/A");
        }
//        setCustomerName(order.getCustomer().getEntity().getContactName());
        setPaid(order.getPaid());
        setPaymentMethod(order.getPaymentMethod().getLabel());
        setStatus(order.getStatus().getLabel());
        if (order.getDelivery() != null) {
            setPlannedDate(order.getDelivery().getPlannedDate());
            setDeliveredDate(order.getDelivery().getDeliveredDate());
            setDeliveryMan(order.getDelivery().getEmployee().getEntity().getContactName());
        }
    }

    public Integer getReference() {
        return reference;
    }

    public void setReference(Integer reference) {
        this.reference = reference;
    }

    public String getStartingDate() {
        return dateFormat(startingDate);
    }

    public void setStartingDate(GregorianCalendar startingDate) {
        this.startingDate = startingDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        if(paid){
            this.paid = "Paid";
        } else {
            this.paid = "Not Paid";
        }
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPlannedDate() {
        return (plannedDate == null) ? "N/A" : dateFormat(plannedDate);
    }

    public void setPlannedDate(GregorianCalendar plannedDate) {
        this.plannedDate = plannedDate;
    }

    public String getDeliveredDate() {
        return (deliveredDate == null) ? ((plannedDate == null) ? "N/A" : "Not delivered yet") : dateFormat(deliveredDate);
    }

    public void setDeliveredDate(GregorianCalendar deliveredDate) {
        this.deliveredDate = deliveredDate;
    }

    public String getDeliveryMan() {
        return (deliveryMan == null) ? "N/A" : deliveryMan;
    }

    public void setDeliveryMan(String deliveryMan) {
        this.deliveryMan = deliveryMan;
    }

    public String dateFormat(GregorianCalendar date){
        return fmt.format(date.getTime());
    }
}
