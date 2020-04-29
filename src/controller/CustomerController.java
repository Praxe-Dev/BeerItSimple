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

    /**
     * Ask the business package to get allCustomer from DB
     * @return an array of CustomerViewTable that contains the right format to be displayed
     */
    public ObservableList<CustomerTableView> getAllCustomers() {
        ArrayList<Customer> customersList = customerBusiness.getAllCustomers();
        ObservableList<CustomerTableView> allCustomers = FXCollections.observableArrayList();
        CustomerTableView customerView;

        for (Customer customer : customersList) {
            customerView = new CustomerTableView(customer.getEntity().getId(), customer.getEntity().getContactName(), customer.getEntity().getPhoneNumber(), customer.getEntity().getMail());
            allCustomers.add(customerView);
        }

        return allCustomers;
    }

    public void newCustomer() {
    }
}
