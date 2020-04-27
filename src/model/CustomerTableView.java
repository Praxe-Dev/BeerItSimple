package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CustomerTableView {
//    EntityId, e.contactName, e.phoneNumber, e.mail
    private IntegerProperty id;
    private StringProperty contactName;
    private StringProperty phoneNumber;
    private StringProperty mail;

    /**
     * Allow to display customer info in different format in curstomerTable
     * @param id
     * @param contactName
     * @param phoneNumber
     * @param mail
     */
    public CustomerTableView(int id, String contactName, String phoneNumber, String mail) {
        setId(id);
        setContactName(contactName);
        setPhoneNumber(phoneNumber);
        setMail(mail);
    }

    // contactName
    public void setContactName(String value) {
        contactNameProperty().set(value);
    }

    public String getContactname() {
        return contactNameProperty().get();
    }

    public StringProperty contactNameProperty() {
    if (contactName == null)
        contactName = new SimpleStringProperty(this, "contactName");

    return contactName;
    }

    // id
    public void setId(Integer id) {
        idProperty().set(id);
    }

    public int getId() {
        return idProperty().get();
    }

    public IntegerProperty idProperty() {
        if (id == null)
            id = new SimpleIntegerProperty(this, "id");

        return id;
    }

    // phoneNumber
    public void setPhoneNumber(String value) {
        phoneNumberProperty().set(value);
    }

    public String getPhoneNumber() {
        return phoneNumberProperty().get();
    }

    public StringProperty phoneNumberProperty() {
        if (phoneNumber == null)
            phoneNumber = new SimpleStringProperty(this, "phoneNumber");

        return phoneNumber;
    }

    // mail
    public void setMail(String value) {
        mailProperty().set(value);
    }

    public String getMail() {
        return mailProperty().get();
    }

    public StringProperty mailProperty() {
        if (mail == null)
            mail = new SimpleStringProperty(this, "mail");

        return mail;
    }

    @Override
    public String toString() {
        return "Id : " + getId() + "\n\tName : " + getContactname();
    }
}
