package controller;

import business.CustomerBusiness;
import exception.CustomerException;
import exception.NoCustomerFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import model.CustomerTableFormat;

import java.sql.SQLException;
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
    public ArrayList<Customer> getAllCustomers() {
        return customerBusiness.getAllCustomers();
    }

    public boolean create(Customer customer) throws SQLException {
        return customerBusiness.create(customer);
    }

    public Customer getCustomer(Integer id) throws CustomerException, NoCustomerFoundException {
        return customerBusiness.getCustomer(id);
    }

    public boolean update(Customer customer) {
        return customerBusiness.update(customer);
    }
}
