package dataAccess;

import exception.ConnectionException;
import exception.DataQueryException;
import model.Status;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StatusDBAccess implements StatusDataAccess {

    private Connection connection;

    public StatusDBAccess() throws ConnectionException {
        this.connection = DBConnection.getInstance();
    }

    @Override
    public ArrayList<Status> getAllStatus() throws DataQueryException {
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
            throw new DataQueryException();
        }

        return statusArrayList;
    }
}
