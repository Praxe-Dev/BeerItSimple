package dataAccess;

import exception.CustomerException;
import exception.DuplicataException;
import exception.CustomerNotFoundException;
import model.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDataAccess {
    public ArrayList<Customer> getAllCustomers();

    public boolean create(Customer customer) throws SQLException;

    public Customer getCustomer(Integer id) throws CustomerException, CustomerNotFoundException;

    boolean update(Customer customer) throws DuplicataException;

    boolean delete(Customer customer);
}
