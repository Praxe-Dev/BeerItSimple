package dataAccess;

import model.PaymentMethod;
import model.Status;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StatusDBAccess implements StatusDataAccess {

    private Connection connection;

    public StatusDBAccess() {
        this.connection = DBConnection.getDBConnection();
    }

    @Override
    public ArrayList<Status> getAllStatus() {
        String sqlInstruction = "SELECT * FROM status";

        ArrayList<Status> statusArrayList = new ArrayList<>();
        Status status;

        try {
            ResultSet data = connection.createStatement().executeQuery(sqlInstruction);

            while (data.next()) {
                status = new Status(
                        data.getInt("id"),
                        data.getString("label")
                );
                statusArrayList.add(status);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return statusArrayList;
    }
}
