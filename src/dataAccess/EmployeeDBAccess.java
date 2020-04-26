package dataAccess;

import exception.EmployeeLoginException;
import model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmployeeDBAccess implements EmployeeDataAccess {
    private Connection connection;

    public EmployeeDBAccess() {
        this.connection = DBConnection.getDBConnection();
    }

    @Override
    public Employee getEmployee(Employee employee) throws EmployeeLoginException {
        String sqlInstructions = "select * from employee where EntityId = ? and password = ?";
        ResultSet employeeData;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstructions);
            preparedStatement.setInt(1, employee.getId());
            preparedStatement.setString(2, employee.getPassword());
            employeeData = preparedStatement.executeQuery();
            employeeData.next();

            if (!employeeData.wasNull()) {
                return new Employee(employee.getId(), employeeData.getInt("RoleId"), employee.getPassword());
            }
        } catch (Exception e) {
            throw new EmployeeLoginException();
        }

        return null;
    }
}
