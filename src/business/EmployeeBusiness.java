package business;

import dataAccess.EmployeeDBAccess;
import exception.EmployeeLoginException;
import model.Employee;

public class EmployeeBusiness {
    private EmployeeDBAccess dao;

    public EmployeeBusiness() {
        setDao(new EmployeeDBAccess());
    }

    private void setDao(EmployeeDBAccess employeeDBAccess) {
        this.dao = employeeDBAccess;
    }

    public Employee getEmployee(Employee employee) throws EmployeeLoginException {
        return dao.getEmployee(employee);
    }
}
