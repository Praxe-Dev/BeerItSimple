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

    public boolean create(Customer customer) throws DuplicataException, NullPointerException {
        if (customer == null)
            throw new NullPointerException();

        return dao.create(customer);
    }

    public Customer getCustomer(Integer id) throws DataQueryException, NullObjectException {
        if (id == null)
            throw new NullObjectException(id.getClass().getName());

        return dao.getCustomer(id);
    }

    public boolean update(Customer customer) throws DuplicataException, UpdateException, NullObjectException {
        if (customer == null)
            throw new NullObjectException(customer.getClass().getName());

        return dao.update(customer);
    }

    public boolean delete(Customer customer) throws DeletionException, NullObjectException {
        if (customer == null)
            throw new NullObjectException(customer.getClass().getName());

        return dao.delete(customer);
    }
}
