package model;


public class CustomerTableFormat {
    private Integer id;
    private String contactName;
    private String phoneNumber;
    private String address;
    private String subscriptionDate = "test";
    private String bankAccountNumber;
    private String businessNumber;
    private String VATNumber;
    private String cityLabel;
    private Integer zipCode;
    private String rankLabel;
    private Integer creditLimit;

    public CustomerTableFormat(Customer customer) {
        this.id = customer.getEntity().getId();
        this.contactName = customer.getEntity().getContactName();
        this.phoneNumber = customer.getEntity().getPhoneNumber();
        this.address = customer.getEntity().getStreet() + ", " + customer.getEntity().getHouseNumber();
//        String dateFormat = new SimpleDateFormat("dd-MM-yyyy").format(customer.getDate());
//        System.out.println("Format de date : " + dateFormat);
//        this.subscriptionDate = new SimpleDateFormat("dd-MM-yyyy").format(customer.getDate());
//        setSubscriptionDate();
//        this.subscriptionDate = "24/14/20132";
        this.bankAccountNumber = customer.getEntity().getBankAccountNumber();
        this.businessNumber = customer.getEntity().getBusinessNumber();
        this.VATNumber = customer.getEntity().getVATNumber();
        this.cityLabel = customer.getEntity().getCity().getLabel();
        this.zipCode = customer.getEntity().getCity().getZipCode();
        this.rankLabel = customer.getRank().getLabel();
        this.creditLimit = customer.getRank().getCreditLimit();
    }

    private void setSubscriptionDate() {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
        this.subscriptionDate = "test";
    }

    private String getSubscriptionDate() {
        return subscriptionDate;
    }

//    public GregorianCalendar getsubscriptionDate() {
//        return subscriptionDate;
//    }
//
//    public void setsubscriptionDate(GregorianCalendar subscriptionDate) {
//        this.subscriptionDate = subscriptionDate;
//    }

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

    public String getVATNumber() {
        return VATNumber;
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
}
