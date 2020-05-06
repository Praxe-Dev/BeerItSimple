package dataAccess;

import exception.DeletionExceiption;
import exception.NoRowSelected;
import exception.SQLManageException;
import model.Customer;
import model.Order;
import model.OrderTableFormat;
import model.Rank;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface OrderDataAccess {
    public ArrayList<Order> getAllOrders() throws SQLException;

    boolean create(Order orderLineTableFormats) throws SQLException, SQLManageException;

    Order getOrder(int reference) throws NoRowSelected;

    boolean deleteOrder(Order order) throws DeletionExceiption;

    boolean updateOrder(Order order) throws SQLManageException;

    ArrayList<Order> getAllOrdersBetweenDates(LocalDate startingDate, LocalDate endDate);

    Rank updateCustomerRank(Customer customer) throws SQLManageException;
}
