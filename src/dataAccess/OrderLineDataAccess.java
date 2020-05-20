package dataAccess;

import javafx.scene.control.DatePicker;
import model.OrderLine;

import java.time.LocalDate;
import java.util.ArrayList;

public interface OrderLineDataAccess {
    ArrayList<OrderLine> getAllOrderLineBetweenDates(LocalDate startDate, LocalDate endDate);
}
