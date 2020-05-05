package controller;

import business.OrderBusiness;
import exception.DeletionExceiption;
import exception.NoRowSelected;
import model.Order;
import model.OrderLineTableFormat;
import model.OrderTableFormat;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class OrderController {
    private OrderBusiness orderBusiness;

    public OrderController() {
        this.orderBusiness = new OrderBusiness();
    }

    public ArrayList<Order> getAllOrders() throws SQLException {
        return orderBusiness.getAllOrders();
    }

    public boolean create(Order orderLineTableFormats) throws SQLException {
        return orderBusiness.create(orderLineTableFormats);
    }

    public Order getOrder(int reference) throws NoRowSelected {
        return orderBusiness.getOrder(reference);
    }

    public boolean deleteOrder(Order order) throws DeletionExceiption {
        return orderBusiness.deleteOrder(order);
    }

    public ArrayList<Order> getAllOrdersBetweenDates(LocalDate startingDate, LocalDate endDate) {
        return orderBusiness.getAllOrdersBetweenDates(startingDate, endDate);
    }
}
