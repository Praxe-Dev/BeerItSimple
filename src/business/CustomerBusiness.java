package business;

import dataAccess.CustomerDBAccess;
import dataAccess.CustomerDataAccess;
import model.Customer;

import java.util.ArrayList;

public class CustomerBusiness {
    private CustomerDataAccess dao;

    public CustomerBusiness() {
        setDao(new CustomerDBAccess());
    }

    public void setDao(CustomerDataAccess dao) {
        this.dao = dao;
    }

    public ArrayList<Customer> getAllCustomers() {
        return dao.getAllCustomers();
    }
}
