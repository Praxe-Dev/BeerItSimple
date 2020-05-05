package view.customer;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.Group;
import model.Customer;
import view.View;


public class Read extends View {

    @FXML
    JFXRadioButton privateCustomer;
    @FXML
    JFXRadioButton businessCustomer;
    @FXML
    JFXTextField customerRank;
    @FXML
    JFXTextField contactName;
    @FXML
    JFXTextField phoneNumber;
    @FXML
    JFXTextField mail;
    @FXML
    JFXTextField houseNumber;
    @FXML
    JFXTextField region;
    @FXML
    JFXTextField address;
    @FXML
    JFXTextField accountNumber;
    @FXML
    JFXTextField businessNumber;
    @FXML
    Group businessView;
    @FXML
    JFXButton closeBtn;
    @FXML
    JFXButton viewOrdersBtn;

    Customer selectedCustomer;


    @Override
    public void init() {

        contactName.setText(selectedCustomer.getEntity().getContactName());
        address.setText(selectedCustomer.getEntity().getStreet());
        houseNumber.setText(selectedCustomer.getEntity().getHouseNumber().toString());
        phoneNumber.setText(selectedCustomer.getEntity().getPhoneNumber());
        region.setText(selectedCustomer.getEntity().getCity().getZipCode().toString());
        customerRank.setText(selectedCustomer.getRank().toString());

        if (selectedCustomer.getEntity().getMail() == null) {
            mail.setText("");
        } else {
            mail.setText(selectedCustomer.getEntity().getMail());
        }

        if (selectedCustomer.getEntity().getBankAccountNumber() == null) {
            accountNumber.setText("");
        } else {
            accountNumber.setText(selectedCustomer.getEntity().getBankAccountNumber());
        }

        if (selectedCustomer.getEntity().getBusinessNumber() == null) {
            businessNumber.setText("");
        } else {
            businessNumber.setText(selectedCustomer.getEntity().getBusinessNumber());
        }

        if (selectedCustomer.getEntity().getBusinessNumber() != null || selectedCustomer.getEntity().getBankAccountNumber() != null) {
            businessView.setVisible(true);
            businessCustomer.setSelected(true);

        } else {
            businessView.setVisible(false);
            privateCustomer.setSelected(true);
        }



        closeBtn.setOnAction(e -> {
            closeWindow();
        });

        /*
        viewOrdersBtn.setOnAction(e -> {

        });
        */

    }

    public void setCustomer(Customer customer) {
        this.selectedCustomer = customer;
    }
}
