package business;

import dataAccess.CustomerDBAccess;
import model.Customer;

import java.util.ArrayList;

public class CustomerBusiness {
    private CustomerDBAccess dao;

    public CustomerBusiness() {
        this.dao = new CustomerDBAccess();
    }

    public ArrayList<Customer> getAllCustomers() {
        return dao.getAllCustomers();
    }
}
