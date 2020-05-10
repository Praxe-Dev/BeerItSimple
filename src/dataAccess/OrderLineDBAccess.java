package dataAccess;

import javafx.scene.control.DatePicker;
import model.Order;
import model.OrderLine;
import model.Product;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class OrderLineDBAccess implements OrderLineDataAccess {
    Connection connection = DBConnection.getDBConnection();

    @Override
    public ArrayList<OrderLine> getAllOrderLineBetweenDates(LocalDate startDate, LocalDate endDate) {

        String sqlInstructions = "SELECT ol.* FROM OrderLine ol\n" +
                                 "JOIN `order` o ON o.reference = ol.Orderreference\n" +
                                 "WHERE ? <= o.startingDate AND ? >= o.startingDate";

        ArrayList<OrderLine> orderLinesList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstructions);
            Date start = Date.valueOf(startDate);
            Date end = Date.valueOf(endDate);
            preparedStatement.setDate(1, start);
            preparedStatement.setDate(2, end);

            ResultSet data = preparedStatement.executeQuery();


            OrderLine ol;
            while (data.next()) {
                ol = new OrderLine(
                        new Product(data.getInt("ol.Productcode")),
                        null,
                        data.getInt("ol.quantity"),
                        data.getDouble("ol.salesUnitPrice")
                );

                orderLinesList.add(ol);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return orderLinesList;
    }
}
