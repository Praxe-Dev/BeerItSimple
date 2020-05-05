package dataAccess;

import exception.DeletionExceiption;
import exception.NoRowSelected;
import exception.SQLManageException;
import model.Order;
import model.OrderTableFormat;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDataAccess {
    public ArrayList<Order> getAllOrders() throws SQLException;

    boolean create(Order orderLineTableFormats) throws SQLException;

    Order getOrder(int reference) throws NoRowSelected;

    boolean deleteOrder(Order order) throws DeletionExceiption;

    boolean updateOrder(Order order) throws SQLManageException;
}
