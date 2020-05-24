package business;

import dataAccess.OrderDBAccess;
import dataAccess.OrderDataAccess;
import exception.*;
import model.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class OrderBusiness {
    private OrderDataAccess dao;

    public OrderBusiness() throws ConnectionException {
        setDao(new OrderDBAccess());
    }

    public void setDao(OrderDataAccess dao) {
        this.dao = dao;
    }

    public ArrayList<Order> getAllOrders() throws DataQueryException {
        return dao.getAllOrders();
    }

    public boolean create(Order orderLineTableFormats) throws UpdateException {
        return dao.create(orderLineTableFormats);
    }

    public Order getOrder(int reference) throws NoRowSelected {
        return dao.getOrder(reference);
    }

    public boolean deleteOrder(Order order) throws DeletionExceiption {
        return dao.deleteOrder(order);
    }

    public ArrayList<Order> getAllOrdersBetweenDates(LocalDate startingDate, LocalDate endDate) throws DataQueryException {
        return dao.getAllOrdersBetweenDates(startingDate, endDate);
    }

    public boolean updateOrder(Order order) throws DataQueryException, UpdateException {
        return dao.updateOrder(order);
    }

    public Rank updateCustomerRank(Customer customer) throws UpdateException {
        return dao.updateCustomerRank(customer);
    }

    public ArrayList<Order> getAllOrdersFromCustomer(Customer customer) throws DataQueryException {
        return dao.getAllOrdersFromCustomer(customer);
    }

    public ArrayList<Order> getAllOrdersFromZipCode(City city) throws DataQueryException {
        return dao.getAllOrdersFromZipCode(city);
    }

    public ArrayList<Order> getOrdersFromRanks(Rank rank, Status status, Boolean isPaid) throws DataQueryException {
        return dao.getOrdersFromRanks(rank, status, isPaid);
    }
}
