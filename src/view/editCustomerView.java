package view;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import model.Customer;

public class editCustomerView extends View {

    @FXML
    JFXComboBox customerRank;
    @FXML
    JFXTextField contactName;
    @FXML
    JFXTextField address;
    @FXML
    JFXTextField houseNumber;
    @FXML
    JFXTextField mail;
    @FXML
    JFXTextField phoneNumber;
    @FXML
    JFXComboBox region;
    @FXML
    JFXTextField accountNumber;
    @FXML
    JFXTextField businessNumber;

    Customer selectedCustomer;

    @Override
    public void init() {
//        Customer customer = CustomersView.getSelectedCustomer();
//        System.out.println(customer);
    }

    protected void getCustomer(Customer customer) {
        this.selectedCustomer = customer;
    }
}
