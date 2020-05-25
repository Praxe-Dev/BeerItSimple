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

    public ArrayList<Customer> getAllCustomers() throws DataQueryException {
        return customerBusiness.getAllCustomers();
    }

    public boolean create(Customer customer) throws DuplicataException, NullObjectException {
        if (customer == null)
            throw new NullObjectException(customer.getClass().getName());

        return customerBusiness.create(customer);
    }

    public Customer getCustomer(Integer id) throws DataQueryException, NullObjectException {
        return customerBusiness.getCustomer(id);
    }

    public boolean update(Customer customer) throws DuplicataException, UpdateException, NullObjectException {
        return customerBusiness.update(customer);
    }

    public boolean delete(Customer customer) throws DeletionException, NullObjectException {
        return customerBusiness.delete(customer);
    }
}
