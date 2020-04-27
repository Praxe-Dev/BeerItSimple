package controller;

import business.EmployeeBusiness;
import exception.EmployeeLoginException;
import model.Employee;

public class EmployeeController {
    private EmployeeBusiness employeeBusiness;

    public EmployeeController() {
        setEmployeeBusiness(new EmployeeBusiness());
    }

    private void setEmployeeBusiness(EmployeeBusiness employeeBusiness) {
        this.employeeBusiness = employeeBusiness;
    }

    public Employee getEmployee(int matricule, String password) throws EmployeeLoginException {
        Employee e = new Employee(matricule, password);
        return employeeBusiness.getEmployee(e);
    }
}
