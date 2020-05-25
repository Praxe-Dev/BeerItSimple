package model;


import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class CustomerTableFormat {
    private Integer id;
    private String contactName;
    private String phoneNumber;
    private String mail;
    private String address;
    private String subscriptionDate;
    private String bankAccountNumber;
    private String businessNumber;
    private String cityLabel;
    private Integer zipCode;
    private String rankLabel;
    private Integer creditLimit;

    public CustomerTableFormat(Customer customer) {
        Entity entityCustomer = customer.getEntity();
        setId(entityCustomer.getId());
        setContactName(entityCustomer.getContactName());
        setPhoneNumber(entityCustomer.getPhoneNumber());
        setMail(entityCustomer.getMail());
        setAddress(entityCustomer.getStreet() + ", " + entityCustomer.getHouseNumber());
        setBankAccountNumber(entityCustomer.getBankAccountNumber());
        setBusinessNumber(entityCustomer.getBusinessNumber());
        setCityLabel(entityCustomer.getCity().getLabel());
        setZipCode(entityCustomer.getCity().getZipCode());
        setRankLabel(customer.getRank().getLabel());
        setCreditLimit(customer.getRank().getCreditLimit());
        setSubscriptionDate(customer.getDate());
    }

    public void setSubscriptionDate(GregorianCalendar subscriptionDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        this.subscriptionDate = formatter.format(subscriptionDate.getTime());
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setSubscriptionDate(String subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public void setBusinessNumber(String businessNumber) {
        this.businessNumber = businessNumber;
    }

    public void setCityLabel(String cityLabel) {
        this.cityLabel = cityLabel;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public void setRankLabel(String rankLabel) {
        this.rankLabel = rankLabel;
    }

    public void setCreditLimit(Integer creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getSubscriptionDate() {
        return this.subscriptionDate;
    }

    public Integer getId() {
        return id;
    }

    public String getContactName() {
        return contactName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public String getBusinessNumber() {
        return businessNumber;
    }

    public String getCityLabel() {
        return cityLabel;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public String getRankLabel() {
        return rankLabel;
    }

    public Integer getCreditLimit() {
        return creditLimit;
    }

    public String getMail() {
        return this.mail;
    }
}
