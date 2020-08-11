package model;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class OrderTableFormat {
    private Integer reference;
    private GregorianCalendar startingDate;
    private Integer customerId;
    private String customerName;
    private String customerRank;
    private String paid;
    private String paymentMethod;
    private String status;
    private String customerAddress;
    private String customerCity;
    private String customerPhoneNumber;
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
            setCustomerName("Deleted Customer");
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

        if (order.getCustomer().getEntity() != null) {
            setCustomerId(order.getCustomer().getEntity().getId());
            setCustomerRank(order.getCustomer().getRank().getLabel());
            setCustomerAddress(order.getCustomer().getEntity().getStreet() + ", " + order.getCustomer().getEntity().getHouseNumber());
            setCustomerCity(order.getCustomer().getEntity().getCity().getLabel());
            setCustomerPhoneNumber(order.getCustomer().getEntity().getPhoneNumber());
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

    public Integer getCustomerId() { return customerId; }

    public void setCustomerId(Integer customerId) { this.customerId = customerId; }

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

    public String getCustomerRank() {
        return customerRank;
    }

    public void setCustomerRank(String customerRank) {
        this.customerRank = customerRank;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }
}
