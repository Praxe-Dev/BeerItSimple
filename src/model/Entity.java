package model;

public class Entity {
    private Integer id;
    private String mail;
    private String contactName;
    private String phoneNumber;
    private Integer houseNumber;
    private String street;
    private String fax;
    private String bankAccountNumber;
    private String VATNumber;
    private String cityLabel;
    private Integer cityZipCode;

    public Entity(Integer id, String mail, String contactName, String phoneNumber, Integer houseNumber, String street, String fax, String bankAccountNumber, String VATNumber, String cityLabel, Integer cityZipCode) {
        this.id = id;
        this.mail = mail;
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
        this.houseNumber = houseNumber;
        this.street = street;
        this.fax = fax;
        this.bankAccountNumber = bankAccountNumber;
        this.VATNumber = VATNumber;
        this.cityLabel = cityLabel;
        this.cityZipCode = cityZipCode;
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
        this.mail = mail;
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
        this.phoneNumber = phoneNumber;
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

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getVATNumber() {
        return VATNumber;
    }

    public void setVATNumber(String VATNumber) {
        this.VATNumber = VATNumber;
    }

    public String getCityLabel() {
        return cityLabel;
    }

    public void setCityLabel(String cityLabel) {
        this.cityLabel = cityLabel;
    }

    public Integer getCityZipCode() {
        return cityZipCode;
    }

    public void setCityZipCode(Integer cityZipCode) {
        this.cityZipCode = cityZipCode;
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
                ", fax='" + fax + '\'' +
                ", bankAccountNumber='" + bankAccountNumber + '\'' +
                ", VATNumber='" + VATNumber + '\'' +
                ", cityLabel='" + cityLabel + '\'' +
                ", cityZipCode=" + cityZipCode +
                '}';
    }
}
