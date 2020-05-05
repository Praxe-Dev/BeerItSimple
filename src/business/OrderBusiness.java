package business;

import dataAccess.OrderDBAccess;
import dataAccess.OrderDataAccess;
import exception.DeletionExceiption;
import exception.NoRowSelected;
import exception.SQLManageException;
import model.Customer;
import model.Order;
import model.OrderLineTableFormat;
import model.OrderTableFormat;

import java.sql.SQLException;
import java.time.LocalDate;
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

    public boolean create(Order orderLineTableFormats) throws SQLException {
        return dao.create(orderLineTableFormats);
    }

    public Order getOrder(int reference) throws NoRowSelected {
        return dao.getOrder(reference);
    }

    public boolean deleteOrder(Order order) throws DeletionExceiption {
        return dao.deleteOrder(order);
    }

    public ArrayList<Order> getAllOrdersBetweenDates(LocalDate startingDate, LocalDate endDate) {
        return dao.getAllOrdersBetweenDates(startingDate, endDate);
    }

    public boolean updateOrder(Order order) throws SQLManageException {
        return dao.updateOrder(order);
    }
}
