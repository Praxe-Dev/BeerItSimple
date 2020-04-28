package dataAccess;

import model.Customer;
import model.Rank;

import java.util.ArrayList;

public interface CustomerDataAccess {
    public ArrayList<Customer> getAllCustomers();
}
