package dataAccess;

import exception.*;
import model.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface OrderDataAccess {
    public ArrayList<Order> getAllOrders() throws DataQueryException;

    boolean create(Order orderLineTableFormats) throws UpdateException;

    Order getOrder(int reference) throws NoRowSelected;

    boolean deleteOrder(Order order) throws DeletionExceiption;

    boolean updateOrder(Order order) throws DataQueryException, UpdateException;

    ArrayList<Order> getAllOrdersBetweenDates(LocalDate startingDate, LocalDate endDate);

    ArrayList<Order> getAllOrdersFromCustomer(Customer customer) throws DataQueryException;

    ArrayList<Order> getAllOrdersFromZipCode(City city) throws SQLManageException;

    Rank updateCustomerRank(Customer customer) throws UpdateException;

    ArrayList<Order> getOrdersFromRanks(Rank rank, Status status, Boolean isPaid);
}
