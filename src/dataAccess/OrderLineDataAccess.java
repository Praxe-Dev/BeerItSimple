package dataAccess;

import exception.DataQueryException;
import model.OrderLine;

import java.time.LocalDate;
import java.util.ArrayList;

public interface OrderLineDataAccess {
    ArrayList<OrderLine> getAllOrderLineBetweenDates(LocalDate startDate, LocalDate endDate) throws DataQueryException;
}
