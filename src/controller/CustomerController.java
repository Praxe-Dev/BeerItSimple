package controller;

import business.CustomerBusiness;
import exception.*;
import model.Customer;

import java.util.ArrayList;

public class CustomerController {
    private CustomerBusiness customerBusiness;

    public CustomerController() throws ConnectionException {
        this.customerBusiness = new CustomerBusiness();
    }

    /**
     * Ask the business package to get allCustomer from DB
     * @return an array of CustomerViewTable that contains the right format to be displayed
     */
    public ArrayList<Customer> getAllCustomers() throws DataQueryException {
        return customerBusiness.getAllCustomers();
    }

    public boolean create(Customer customer) throws DuplicataException {
        return customerBusiness.create(customer);
    }

    public Customer getCustomer(Integer id) throws DataQueryException {
        return customerBusiness.getCustomer(id);
    }

    public boolean update(Customer customer) throws DuplicataException, UpdateException {
        return customerBusiness.update(customer);
    }

    public boolean delete(Customer customer) throws DeletionException {
        return customerBusiness.delete(customer);
    }
}
