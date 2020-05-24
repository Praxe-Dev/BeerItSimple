package controller;

import business.CustomerBusiness;
import exception.*;
import model.Customer;

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

    public boolean create(Customer customer) throws CustomerInsertionException {
        return customerBusiness.create(customer);
    }

    public Customer getCustomer(Integer id) throws CustomerException, CustomerNotFoundException {
        return customerBusiness.getCustomer(id);
    }

    public boolean update(Customer customer) throws CustomerUpdateException {
        return customerBusiness.update(customer);
    }

    public boolean delete(Customer customer) {
        return customerBusiness.delete(customer);
    }
}
