package controller;

import business.OrderBusiness;
import model.Order;
import model.OrderLineTableFormat;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderController {
    private OrderBusiness orderBusiness;

    public OrderController() {
        this.orderBusiness = new OrderBusiness();
    }

    public ArrayList<Order> getAllOrders() throws SQLException {
        return orderBusiness.getAllOrders();
    }

    public boolean create(Order orderLineTableFormats) {
        return orderBusiness.create(orderLineTableFormats);
    }
}
