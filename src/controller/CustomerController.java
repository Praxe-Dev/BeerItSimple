package controller;

import business.CustomerBusiness;
import exception.CustomerException;
import exception.DuplicataException;
import exception.CustomerNotFoundException;
import model.Customer;

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

    public Customer getCustomer(Integer id) throws CustomerException, CustomerNotFoundException {
        return customerBusiness.getCustomer(id);
    }

    public boolean update(Customer customer) throws DuplicataException {
        return customerBusiness.update(customer);
    }

    public boolean delete(Customer customer) {
        return customerBusiness.delete(customer);
    }
}
