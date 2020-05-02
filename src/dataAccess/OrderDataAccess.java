package dataAccess;

import model.Order;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDataAccess {
    public ArrayList<Order> getAllOrders() throws SQLException;
}
