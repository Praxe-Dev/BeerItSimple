package dataAccess;

import model.Employee;
import model.Entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDataTest {
    private Employee employee;

    public static boolean login(int id, String password) {
        Connection connection = DBConnection.getDBConnection();
        String sqlInstructions = "select * from employee where EntityId = ? and password = ?";
        ResultSet employeeData;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstructions);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, password);
            employeeData = preparedStatement.executeQuery();
            employeeData.next();

            if (!employeeData.wasNull()) {
                System.out.println("Employee trouver - id : " + employeeData.getInt("EntityId") + " Password : " + employeeData.getString("password"));
                return true;
            }
        } catch (SQLException e) {
            System.out.println("ERREUR SQL - Employee introuvable");
            e.printStackTrace();
            return false;
        }

        return false;
    }
}
