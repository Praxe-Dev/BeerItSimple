package controller;

import business.OrderBusiness;
import exception.*;
import model.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class OrderController {
    private OrderBusiness orderBusiness;

    public OrderController() throws ConnectionException {
        this.orderBusiness = new OrderBusiness();
    }

    public ArrayList<Order> getAllOrders() throws DataQueryException {
        return orderBusiness.getAllOrders();
    }

    public boolean create(Order order) throws UpdateException, NullObjectException {
        return orderBusiness.create(order);
    }

    public Order getOrder(int reference) throws DataQueryException {
        return orderBusiness.getOrder(reference);
    }

    public boolean deleteOrder(Order order) throws DeletionException, NullObjectException {
        return orderBusiness.deleteOrder(order);
    }

    public boolean updateOrder(Order order) throws UpdateException, DataQueryException, NullObjectException {
        return orderBusiness.updateOrder(order);
    }

    public ArrayList<Order> getAllOrdersBetweenDates(LocalDate startingDate, LocalDate endDate) throws DataQueryException, NullObjectException {
        return orderBusiness.getAllOrdersBetweenDates(startingDate, endDate);
    }

    public Rank updateCustomerRank(Customer customer) throws UpdateException, NullObjectException {
        return orderBusiness.updateCustomerRank(customer);
    }

    public ArrayList<Order> getAllOrdersFromCustomer(Customer customer) throws DataQueryException, NullObjectException {
        return orderBusiness.getAllOrdersFromCustomer(customer);
    }

    public ArrayList<Order> getAllOrdersFromZipCode(City city) throws DataQueryException, NullObjectException {
        return orderBusiness.getAllOrdersFromZipCode(city);
    }

    public ArrayList<Order> getOrdersFromRanks(Rank rank, Status status, Boolean isPaid) throws DataQueryException, NullObjectException {
        return orderBusiness.getOrdersFromRanks(rank, status, isPaid);
    }
}
