package controller;

import business.OrderBusiness;
import exception.*;
import model.*;

import java.sql.SQLException;
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

    public boolean create(Order orderLineTableFormats) throws SQLException, SQLManageException {
        return orderBusiness.create(orderLineTableFormats);
    }

    public Order getOrder(int reference) throws NoRowSelected {
        return orderBusiness.getOrder(reference);
    }

    public boolean deleteOrder(Order order) throws DeletionExceiption {
        return orderBusiness.deleteOrder(order);
    }

    public boolean updateOrder(Order order) throws UpdateOrderException, DataQueryException {
        return orderBusiness.updateOrder(order);
    }

    public ArrayList<Order> getAllOrdersBetweenDates(LocalDate startingDate, LocalDate endDate) {
        return orderBusiness.getAllOrdersBetweenDates(startingDate, endDate);
    }

    public Rank updateCustomerRank(Customer customer) throws SQLManageException {
        return orderBusiness.updateCustomerRank(customer);
    }

    public ArrayList<Order> getAllOrdersFromCustomer(Customer customer) throws SQLManageException{
        return orderBusiness.getAllOrdersFromCustomer(customer);
    }

    public ArrayList<Order> getAllOrdersFromZipCode(City city) throws SQLManageException{
        return orderBusiness.getAllOrdersFromZipCode(city);
    }

    public ArrayList<Order> getOrdersFromRanks(Rank rank, Status status, Boolean isPaid) {
        return orderBusiness.getOrdersFromRanks(rank, status, isPaid);
    }
}
