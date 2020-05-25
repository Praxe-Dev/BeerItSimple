package dataAccess;

import exception.*;
import model.Customer;

import java.util.ArrayList;

public interface CustomerDataAccess {
    ArrayList<Customer> getAllCustomers() throws DataQueryException;

    boolean create(Customer customer) throws CustomerInsertionException;

    Customer getCustomer(Integer id) throws DataQueryException;

    boolean update(Customer customer) throws CustomerUpdateException;

    boolean delete(Customer customer);
}
