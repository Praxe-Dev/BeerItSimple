package dataAccess;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public class DBConnection {
    private static Connection connection = null;

    // Modify to suit your config
    private static String url = "jdbc:mysql://localhost:3306/projet_brasserie";
    private static String id = "root";
    private static String password = "didi2000";

    private DBConnection() {
    }

    public static Connection getDBConnection() {
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(url, id, password);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        return connection;
    }
}
