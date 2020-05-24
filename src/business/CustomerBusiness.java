package business;

import dataAccess.CustomerDBAccess;
import dataAccess.CustomerDataAccess;
import exception.CustomerException;
import exception.DuplicataException;
import exception.CustomerNotFoundException;
import exception.InsertionError;
import model.Customer;

import java.sql.SQLException;
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

    public boolean create(Customer customer) throws InsertionError {
        return dao.create(customer);
    }

    public Customer getCustomer(Integer id) throws CustomerException, CustomerNotFoundException {
        return dao.getCustomer(id);
    }

    public boolean update(Customer customer) throws DuplicataException {
        return dao.update(customer);
    }

    public boolean delete(Customer customer) {
        return dao.delete(customer);
    }
}
