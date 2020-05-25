package dataAccess;

import exception.DataQueryException;
import exception.LoginException;
import model.Employee;

import java.util.ArrayList;

public interface EmployeeDataAccess {
    Employee getEmployee(int registrationNumber, String password) throws LoginException;

    ArrayList<Employee> getAllDeliveryEmployee() throws DataQueryException;

    String getEmployeeName(Integer entityId) throws DataQueryException;
}
