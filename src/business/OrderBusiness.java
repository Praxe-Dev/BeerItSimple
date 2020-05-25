package business;

import dataAccess.OrderDBAccess;
import dataAccess.OrderDataAccess;
import exception.*;
import model.*;

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

    public boolean create(Order order) throws UpdateException, NullObjectException {
        if (order == null)
            throw new NullObjectException(order.getClass().getName());

        return dao.create(order);
    }

    public Order getOrder(int reference) throws DataQueryException {
        return dao.getOrder(reference);
    }

    public boolean deleteOrder(Order order) throws DeletionException, NullObjectException {
        if (order == null)
            throw new NullObjectException(order.getClass().getName());

        return dao.deleteOrder(order);
    }

    public ArrayList<Order> getAllOrdersBetweenDates(LocalDate startingDate, LocalDate endDate) throws DataQueryException, NullObjectException {
        if (startingDate == null || endDate == null)
            throw new NullObjectException(startingDate.getClass().getName());

        return dao.getAllOrdersBetweenDates(startingDate, endDate);
    }

    public boolean updateOrder(Order order) throws DataQueryException, UpdateException, NullObjectException {
        if (order == null)
            throw new NullObjectException(order.getClass().getName());

        return dao.updateOrder(order);
    }

    public Rank updateCustomerRank(Customer customer) throws UpdateException, NullObjectException {
        if (customer == null)
            throw new NullObjectException(customer.getClass().getName());

        return dao.updateCustomerRank(customer);
    }

    public ArrayList<Order> getAllOrdersFromCustomer(Customer customer) throws DataQueryException, NullObjectException {
        if (customer == null)
            throw new NullObjectException(customer.getClass().getName());

        return dao.getAllOrdersFromCustomer(customer);
    }

    public ArrayList<Order> getAllOrdersFromZipCode(City city) throws DataQueryException, NullObjectException {
        if (city == null)
            throw new NullObjectException(city.getClass().getName());

        return dao.getAllOrdersFromZipCode(city);
    }

    public ArrayList<Order> getOrdersFromRanks(Rank rank, Status status, Boolean isPaid) throws DataQueryException, NullObjectException {
        if (rank == null)
            throw new NullObjectException(rank.getClass().getName());
        if (status == null)
            throw new NullObjectException(status.getClass().getName());
        if (isPaid == null)
            throw new NullObjectException(isPaid.getClass().getName());

        return dao.getOrdersFromRanks(rank, status, isPaid);
    }
}
