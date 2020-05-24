package dataAccess;

import exception.ConnectionException;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection = null;

    // Modify to suit your settings
    private static String url = "jdbc:mysql://localhost:3306/beeritsimple";
    private static String id = "root";
    private static String password = "root";

    private DBConnection() throws ConnectionException {
        try {
            connection = DriverManager.getConnection(url, id, password);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ConnectionException();
        }
    }

    public static Connection getInstance() throws ConnectionException {
        if (connection == null)
            new DBConnection();

        return connection;
    }
}
