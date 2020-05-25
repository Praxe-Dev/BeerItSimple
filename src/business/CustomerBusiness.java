package business;

import dataAccess.CustomerDBAccess;
import dataAccess.CustomerDataAccess;
import exception.*;
import model.Customer;

import java.util.ArrayList;

public class CustomerBusiness {
    private CustomerDataAccess dao;

    public CustomerBusiness() throws ConnectionException {
        setDao(new CustomerDBAccess());
    }

    public void setDao(CustomerDataAccess dao) {
        this.dao = dao;
    }

    public ArrayList<Customer> getAllCustomers() throws DataQueryException {
        return dao.getAllCustomers();
    }

    public boolean create(Customer customer) throws CustomerInsertionException {
        return dao.create(customer);
    }

    public Customer getCustomer(Integer id) throws DataQueryException {
        return dao.getCustomer(id);
    }

    public boolean update(Customer customer) throws CustomerUpdateException {
        return dao.update(customer);
    }

    public boolean delete(Customer customer) {
        return dao.delete(customer);
    }
}
