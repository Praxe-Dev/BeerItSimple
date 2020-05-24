package controller;

import business.EmployeeBusiness;
import exception.ConnectionException;
import exception.DataQueryException;
import exception.EmployeeLoginException;
import exception.SQLManageException;
import model.Employee;

import java.util.ArrayList;

public class EmployeeController {
    private EmployeeBusiness employeeBusiness;

    public EmployeeController() throws ConnectionException {
        setEmployeeBusiness(new EmployeeBusiness());
    }

    private void setEmployeeBusiness(EmployeeBusiness employeeBusiness) {
        this.employeeBusiness = employeeBusiness;
    }

    public Employee getEmployee(int registrationNumber, String password) throws EmployeeLoginException {
        //Employee e = new Employee(matricule, password);
        return employeeBusiness.getEmployee(registrationNumber, password);
    }

    public ArrayList<Employee> getAllDeliveryEmployee() throws DataQueryException {
        return employeeBusiness.getAllDeliveryEmployee();
    }

    public String getEmployeeName(Integer entityId) throws DataQueryException {
        return employeeBusiness.getEmployeeName(entityId);
    }
}
