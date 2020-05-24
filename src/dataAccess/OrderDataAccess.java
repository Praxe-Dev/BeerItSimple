package dataAccess;

import exception.DataQueryException;
import exception.DeletionExceiption;
import exception.NoRowSelected;
import exception.SQLManageException;
import model.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface OrderDataAccess {
    public ArrayList<Order> getAllOrders() throws DataQueryException;

    boolean create(Order orderLineTableFormats) throws SQLException, SQLManageException;

    Order getOrder(int reference) throws NoRowSelected;

    boolean deleteOrder(Order order) throws DeletionExceiption;

    boolean updateOrder(Order order) throws SQLManageException;

    ArrayList<Order> getAllOrdersBetweenDates(LocalDate startingDate, LocalDate endDate);

    ArrayList<Order> getAllOrdersFromCustomer(Customer customer) throws SQLManageException;

    ArrayList<Order> getAllOrdersFromZipCode(City city) throws SQLManageException;

    Rank updateCustomerRank(Customer customer) throws SQLManageException;

    ArrayList<Order> getOrdersFromRanks(Rank rank, Status status, Boolean isPaid);
}
