package business;

import dataAccess.EmployeeDBAccess;
import dataAccess.EmployeeDataAccess;
import exception.EmployeeLoginException;
import model.Employee;

public class EmployeeBusiness {
    private EmployeeDataAccess dao;

    public EmployeeBusiness() {
        setDao(new EmployeeDBAccess());
    }

    private void setDao(EmployeeDataAccess employeeDBAccess) {
        this.dao = employeeDBAccess;
    }

    public Employee getEmployee(Employee employee) throws EmployeeLoginException {
        return dao.getEmployee(employee);
    }
}
