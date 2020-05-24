package dataAccess;

import exception.*;
import model.Customer;

import java.util.ArrayList;

public interface CustomerDataAccess {
    public ArrayList<Customer> getAllCustomers();

    public boolean create(Customer customer) throws CustomerInsertionException;

    public Customer getCustomer(Integer id) throws CustomerException, CustomerNotFoundException;

    boolean update(Customer customer) throws CustomerUpdateException;

    boolean delete(Customer customer);
}
