package dataAccess;

import exception.EmployeeLoginException;
import model.City;
import model.Employee;
import model.Entity;
import model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmployeeDBAccess implements EmployeeDataAccess {
    private Connection connection;

    public EmployeeDBAccess() {
        this.connection = DBConnection.getDBConnection();
    }

    @Override
    public Employee getEmployee(int registrationNumber, String password) throws EmployeeLoginException {
        System.out.println(registrationNumber);
        System.out.println(password);
        String sqlInstructions = "SELECT employee.*, e.*, r.*, city.* FROM employee\n" +
                                "JOIN entity e ON e.id = employee.EntityId\n" +
                                "JOIN role r ON r.id = employee.RoleId\n" +
                                "JOIN city ON city.label = e.Citylabel AND city.zipCode = e.CityZipCode\n" +
                                "WHERE employee.EntityId = ? AND employee.password = ?";
        ResultSet employeeData;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstructions);
            preparedStatement.setInt(1, registrationNumber);
            preparedStatement.setString(2, password);
            employeeData = preparedStatement.executeQuery();
            employeeData.next();

            if (!employeeData.wasNull()) {
                City city = new City(
                        employeeData.getString("city.label"),
                        employeeData.getInt("city.zipCode")
                );
                System.out.println(city);
                Entity entity = new Entity(
                        employeeData.getInt("e.id"),
                        employeeData.getString("e.mail"),
                        employeeData.getString("e.contactName"),
                        employeeData.getString("e.phoneNumber"),
                        employeeData.getInt("e.houseNumber"),
                        employeeData.getString("e.street"),
                        employeeData.getString("e.bankAccountNumber"),
                        employeeData.getString("e.businessNumber"),
                        city
                );
                System.out.println(entity);
                Role role = new Role(
                        employeeData.getInt("r.id"),
                        employeeData.getString("r.name")
                );
                System.out.println(role);
                return new Employee(entity, role, password);
            }
        } catch (Exception e) {
            throw new EmployeeLoginException();
        }

        return null;
    }
}
