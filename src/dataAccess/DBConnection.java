package dataAccess;

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

    private DBConnection() {
        try {
            connection = DriverManager.getConnection(url, id, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // TODO: remplacer par getinstance
    public static Connection getDBConnection() {
        if (connection == null)
            new DBConnection();

        return connection;
    }
}
