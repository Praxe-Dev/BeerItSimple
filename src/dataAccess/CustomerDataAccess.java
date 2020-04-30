package dataAccess;

import exception.CustomerException;
import model.Customer;
import model.Rank;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDataAccess {
    public ArrayList<Customer> getAllCustomers();

    public boolean create(Customer customer) throws SQLException;
}
