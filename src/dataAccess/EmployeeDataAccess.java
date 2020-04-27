package dataAccess;

import exception.EmployeeLoginException;
import model.Employee;

public interface EmployeeDataAccess {
    public Employee getEmployee(Employee employee) throws EmployeeLoginException;
}
