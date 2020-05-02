package controller;

import business.OrderBusiness;
import model.Order;

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
}
