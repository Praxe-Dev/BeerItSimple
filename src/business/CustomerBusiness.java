package business;

import dataAccess.CustomerDBAccess;
import dataAccess.CustomerDataAccess;
import exception.CustomerException;
import exception.DuplicataException;
import exception.NoCustomerFoundException;
import model.Customer;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
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

    public boolean create(Customer customer) throws SQLException {
        return dao.create(customer);
    }

    public Customer getCustomer(Integer id) throws CustomerException, NoCustomerFoundException {
        return dao.getCustomer(id);
    }

    public boolean update(Customer customer) throws DuplicataException {
        return dao.update(customer);
    }
}
