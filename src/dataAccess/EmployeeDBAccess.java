package dataAccess;

import exception.ConnectionException;
import exception.DataQueryException;
import exception.EmployeeLoginException;
import exception.SQLManageException;
import model.City;
import model.Employee;
import model.Entity;
import model.Role;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;

public class EmployeeDBAccess implements EmployeeDataAccess {
    private Connection connection;

    public EmployeeDBAccess() throws ConnectionException {
        this.connection = DBConnection.getInstance();
    }

    @Override
    public Employee getEmployee(int login, String password) throws EmployeeLoginException {
        String sqlInstructions = "SELECT employee.*, e.*, r.*, city.* FROM employee\n" +
                                "JOIN entity e ON e.id = employee.EntityId\n" +
                                "JOIN role r ON r.id = employee.RoleId\n" +
                                "JOIN city ON city.label = e.Citylabel AND city.zipCode = e.CityZipCode\n" +
                                "WHERE employee.EntityId = ? AND employee.password = ?";
        ResultSet employeeData;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstructions);
            preparedStatement.setInt(1, login);
            preparedStatement.setString(2, password);
            employeeData = preparedStatement.executeQuery();
            employeeData.next();

            if (!employeeData.wasNull()) {
                City city = new City(
                        employeeData.getString("city.label"),
                        employeeData.getInt("city.zipCode")
                );

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

                Role role = new Role(
                        employeeData.getInt("r.id"),
                        employeeData.getString("r.name")
                );

                return new Employee(entity, role, password);
            }
        } catch (Exception e) {
            throw new EmployeeLoginException();
        }

        return null;
    }

    public ArrayList<Employee> getAllDeliveryEmployee() throws DataQueryException {
        ArrayList<Employee> deliveryList = new ArrayList<>();
        String sqlInstruction = "SELECT e.*, entity.*, r.*, c.* FROM employee e JOIN role r ON r.id = e.RoleId JOIN entity ON entity.id = e.EntityId JOIN city c ON c.label = entity.Citylabel AND c.zipCode = entity.CityZipCode WHERE r.id = ?";
        ResultSet employeeData;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, 3);
            employeeData = preparedStatement.executeQuery();
            while(employeeData.next()){
                City city = new City(
                        employeeData.getString("c.label"),
                        employeeData.getInt("c.zipCode")
                );

                Entity entity = new Entity(
                        employeeData.getInt("entity.id"),
                        employeeData.getString("entity.mail"),
                        employeeData.getString("entity.contactName"),
                        employeeData.getString("entity.phoneNumber"),
                        employeeData.getInt("entity.houseNumber"),
                        employeeData.getString("entity.street"),
                        employeeData.getString("entity.bankAccountNumber"),
                        employeeData.getString("entity.businessNumber"),
                        city
                );

                Role role = new Role(
                        employeeData.getInt("r.id"),
                        employeeData.getString("r.name")
                );

                deliveryList.add(new Employee(entity, role));
            }
        } catch(SQLException e){
            throw new DataQueryException();
        }


        return deliveryList;
    }

    public String getEmployeeName(Integer entityId) throws DataQueryException {
        //Return entity name from entityId
        String sqlInstruction = "SELECT contactName FROM entity WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, entityId);
            ResultSet data = preparedStatement.executeQuery();
            if (data.next()) {
                return data.getString("contactName");
            }
        } catch(SQLException e){
            throw new DataQueryException();
        }

        return null;
    }
}
