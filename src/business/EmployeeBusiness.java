package business;

import dataAccess.EmployeeDBAccess;
import dataAccess.EmployeeDataAccess;
import exception.ConnectionException;
import exception.EmployeeLoginException;
import exception.SQLManageException;
import model.Employee;

import java.util.ArrayList;

public class EmployeeBusiness {
    private EmployeeDataAccess dao;

    public EmployeeBusiness() throws ConnectionException {
        setDao(new EmployeeDBAccess());
    }

    private void setDao(EmployeeDataAccess employeeDBAccess) {
        this.dao = employeeDBAccess;
    }

    public Employee getEmployee(int registrationNumber, String password) throws EmployeeLoginException {
        return dao.getEmployee(registrationNumber, password);
    }

    public ArrayList<Employee> getAllDeliveryEmployee() throws SQLManageException {
        return dao.getAllDeliveryEmployee();
    }

    public String getEmployeeName(Integer entityId) throws SQLManageException {
        return dao.getEmployeeName(entityId);
    }
}
