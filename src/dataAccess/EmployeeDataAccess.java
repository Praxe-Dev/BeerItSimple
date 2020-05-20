package dataAccess;

import exception.EmployeeLoginException;
import exception.SQLManageException;
import model.Employee;

import java.util.ArrayList;

public interface EmployeeDataAccess {
    Employee getEmployee(int registrationNumber, String password) throws EmployeeLoginException;

    ArrayList<Employee> getAllDeliveryEmployee() throws SQLManageException;

    public String getEmployeeName(Integer entityId) throws SQLManageException;
}
