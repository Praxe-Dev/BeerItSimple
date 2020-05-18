package dataAccess;

import exception.EmployeeLoginException;
import model.Employee;

public interface EmployeeDataAccess {
    Employee getEmployee(int registrationNumber, String password) throws EmployeeLoginException;
}
