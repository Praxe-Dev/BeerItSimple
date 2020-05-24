package dataAccess;

import exception.*;
import model.Customer;

import java.util.ArrayList;

public interface CustomerDataAccess {
    ArrayList<Customer> getAllCustomers();

    boolean create(Customer customer) throws CustomerInsertionException;

    Customer getCustomer(Integer id) throws CustomerException, CustomerNotFoundException;

    boolean update(Customer customer) throws CustomerUpdateException;

    boolean delete(Customer customer);
}
