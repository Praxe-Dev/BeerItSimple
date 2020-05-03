package model;

import javax.xml.validation.Validator;

public class Entity {
    private Integer id;
    private String mail;
    private String contactName;
    private String phoneNumber;
    private Integer houseNumber;
    private String street;
    private String bankAccountNumber;
    private String businessNumber;
    private String VATNumber;
    private City city;

    public Entity(Integer id, String mail, String contactName, String phoneNumber, Integer houseNumber, String street, String bankAccountNumber, String businessNumber, City city) {
        setId(id);
        setMail(mail);
        setContactName(contactName);
        setPhoneNumber(phoneNumber);
        setHouseNumber(houseNumber);
        setStreet(street);
        setBankAccountNumber(bankAccountNumber);
        setBusinessNumber(businessNumber);
        setVATNumber(businessNumber);
        setCity(city);
    }

    public Entity(){
        this(null, null, null, null, null, null, null, null, null);
    }

    public Entity(Integer id, String contactName, String phoneNumber, Integer houseNumber, String street, City city){
        this(id, null, contactName, phoneNumber, houseNumber, street, null, null, city);
    }

    /*
    public Entity(Integer id, String mail, String contactName, String phoneNumber, Integer houseNumber, String street, String fax, City city) {
        this(id, mail, contactName, phoneNumber, houseNumber, street, fax, null, null, null, city);
    }

    public Entity(String mail, String contactName, String phoneNumber, Integer houseNumber, String street, String fax, City city) {
        this(null, mail, contactName, phoneNumber, houseNumber, street, fax, null, null, null, city);
    }

    public Entity(String contactName, String phoneNumber, Integer houseNumber, String street, City city){
        this(null, null, contactName, phoneNumber, houseNumber, street, null, null, null, null, city);
    }

    public Entity(String contactName, String phoneNumber, Integer houseNumber, String street, String fax, City city){
        this(null, null, contactName, phoneNumber, houseNumber, street, fax, null, null, null, city);
    }

    public Entity(String contactName, String phoneNumber, Integer houseNumber, String street, String businessNumber, String VATNumber,City city){
        this(null, null, contactName, phoneNumber, houseNumber, street, null, null, businessNumber, VATNumber, city);
    }

    public Entity(String mail, String contactName, String phoneNumber, Integer houseNumber, String street, String businessNumber, String VATNumber, City city){
        this(null, mail, contactName, phoneNumber, houseNumber, street, null, null, businessNumber, VATNumber, city);
    }

    public Entity(String mail, String contactName, String phoneNumber, Integer houseNumber, String street, String fax, String businessNumber, String VATNumber, City city){
        this(null, mail, contactName, phoneNumber, houseNumber, street, fax, null, businessNumber, VATNumber, city);
    }

    public Entity(String contactName, String phoneNumber, Integer houseNumber, String street, String bankAccountNumber, String businessNumber, String VATNumber, City city){
        this(null, null, contactName, phoneNumber, houseNumber, street, null, bankAccountNumber, businessNumber, VATNumber, city);
    }
     */

    public String getBusinessNumber() {
        return businessNumber;
    }

    public void setBusinessNumber(String businessNumber) {
        if(businessNumber != null) {
            String businessNumberRegex = "^(\\d{4})\\.(\\d{3})\\.(\\d{3})$";
            if (businessNumber.matches(businessNumberRegex)) this.businessNumber = businessNumber;
        } else {
            this.businessNumber = null;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        if(mail != null) {
            String mailRegex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
            if (mail.matches(mailRegex)) this.mail = mail;
        } else {
            this.mail = null;
        }
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if(phoneNumber != null) {
            String phoneNumberRegex = "^(\\d{4})\\/(\\d{2})\\.(\\d{2})\\.(\\d{2})$";
            if (phoneNumber.matches(phoneNumberRegex)) this.phoneNumber = phoneNumber;
        }
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        if(bankAccountNumber != null) {
            String bankAccountRegex = "^([A-Z]{2})(\\d{14})$";
            if (bankAccountNumber.matches(bankAccountRegex)) this.bankAccountNumber = bankAccountNumber;
        } else {
            this.bankAccountNumber = null;
        }
    }

    public String getVATNumber() {
        return VATNumber;
    }

    public void setVATNumber(String businessNumber) {
        if(businessNumber != null) {
            this.VATNumber = "BE" + businessNumber;
        } else {
            this.VATNumber = null;
        }
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                ", mail='" + mail + '\'' +
                ", contactName='" + contactName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", houseNumber=" + houseNumber +
                ", street='" + street + '\'' +
                ", bankAccountNumber='" + bankAccountNumber + '\'' +
                ", VATNumber='" + VATNumber + '\'' +
                ", cityLabel='" + getCity().getLabel() + '\'' +
                ", cityZipCode=" + getCity().getZipCode() +
                '}';
    }
}
