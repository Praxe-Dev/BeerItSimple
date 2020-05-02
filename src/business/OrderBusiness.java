package business;

import dataAccess.OrderDBAccess;
import dataAccess.OrderDataAccess;
import model.Customer;
import model.Order;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBusiness {
    private OrderDataAccess dao;

    public OrderBusiness() {
        setDao(new OrderDBAccess());
    }

    public void setDao(OrderDataAccess dao) {
        this.dao = dao;
    }

    public ArrayList<Order> getAllOrders() throws SQLException {
        return dao.getAllOrders();
    }
}
