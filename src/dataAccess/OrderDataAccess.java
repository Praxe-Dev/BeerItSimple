package dataAccess;

import exception.*;
import model.*;

import java.time.LocalDate;
import java.util.ArrayList;

public interface OrderDataAccess {
    ArrayList<Order> getAllOrders() throws DataQueryException;

    boolean create(Order orderLineTableFormats) throws UpdateException;

    Order getOrder(int reference) throws DataQueryException;

    boolean deleteOrder(Order order) throws DeletionException;

    boolean updateOrder(Order order) throws DataQueryException, UpdateException;

    ArrayList<Order> getAllOrdersBetweenDates(LocalDate startingDate, LocalDate endDate) throws DataQueryException;

    ArrayList<Order> getAllOrdersFromCustomer(Customer customer) throws DataQueryException;

    ArrayList<Order> getAllOrdersFromZipCode(City city) throws DataQueryException;

    Rank updateCustomerRank(Customer customer) throws UpdateException;

    ArrayList<Order> getOrdersFromRanks(Rank rank, Status status, Boolean isPaid) throws DataQueryException;
}
