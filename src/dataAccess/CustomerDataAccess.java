package dataAccess;

import exception.CustomerException;
import exception.DuplicataException;
import exception.NoCustomerFoundException;
import model.Customer;
import model.Rank;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

public interface CustomerDataAccess {
    public ArrayList<Customer> getAllCustomers();

    public boolean create(Customer customer) throws SQLException;

    public Customer getCustomer(Integer id) throws CustomerException, NoCustomerFoundException;

    boolean update(Customer customer) throws DuplicataException;
}
