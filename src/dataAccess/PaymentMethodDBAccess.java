package dataAccess;

import exception.ConnectionException;
import exception.DataQueryException;
import model.PaymentMethod;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentMethodDBAccess implements PaymentMethodDataAccess {

    private Connection connection;

    public PaymentMethodDBAccess() throws ConnectionException {
        this.connection = DBConnection.getInstance();
    }

    @Override
    public ArrayList<PaymentMethod> getAllPaymentMethod() throws DataQueryException {
        String sqlInstruction = "SELECT * FROM paymentMethod";

        ArrayList<PaymentMethod> paymentMethodList = new ArrayList<>();
        PaymentMethod paymentMethod;

        try {
            ResultSet data = connection.createStatement().executeQuery(sqlInstruction);

            while (data.next()) {
                paymentMethod = new PaymentMethod(
                        data.getInt("id"),
                        data.getString("label")
                );
                paymentMethodList.add(paymentMethod);
            }

        } catch (SQLException e) {
            throw new DataQueryException();
        }

        return paymentMethodList;
    }
}
