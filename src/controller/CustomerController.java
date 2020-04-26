package controller;

import business.CustomerBusiness;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import model.CustomerTableView;

import java.util.ArrayList;

public class CustomerController {
    private CustomerBusiness customerBusiness;

    public CustomerController() {
        this.customerBusiness = new CustomerBusiness();
    }

    public ObservableList<CustomerTableView> getAllCustomers() {
        ArrayList<Customer> customersList = customerBusiness.getAllCustomers();
        ObservableList<CustomerTableView> allCustomers = FXCollections.observableArrayList();
        CustomerTableView customerView;

        for (Customer customer : customersList) {
            customerView = new CustomerTableView(customer.getId(), customer.getContactName(), customer.getPhoneNumber(), customer.getMail());
            allCustomers.add(customerView);
        }

        return allCustomers;
    }
}
