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
        this.id = customer.getEntity().getId();
        this.contactName = customer.getEntity().getContactName();
        this.phoneNumber = customer.getEntity().getPhoneNumber();
        this.mail = customer.getEntity().getMail();
        this.address = customer.getEntity().getStreet() + ", " + customer.getEntity().getHouseNumber();
        setSubscriptionDate(customer.getDate());
        this.bankAccountNumber = customer.getEntity().getBankAccountNumber();
        this.businessNumber = customer.getEntity().getBusinessNumber();
        this.cityLabel = customer.getEntity().getCity().getLabel();
        this.zipCode = customer.getEntity().getCity().getZipCode();
        this.rankLabel = customer.getRank().getLabel();
        this.creditLimit = customer.getRank().getCreditLimit();
    }


    public void setSubscriptionDate(GregorianCalendar subscriptionDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        this.subscriptionDate = formatter.format(subscriptionDate.getTime());
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
