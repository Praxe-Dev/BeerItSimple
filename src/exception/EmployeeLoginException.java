package exception;

import model.Employee;
import view.popUp;

public class EmployeeLoginException extends Exception {
    private Employee employee;
    private final static String typeError = "Connection Error";
    private final static String message = "Login and password doesn't match.";
//    public EmployeeLoginException (Employee employee) {
//        this.employee = employee;
//    }

//    @Override
//    public String getMessage() {
//        // Faire une fenêtre d'info
//        return "Erreur - le login " + employee.getId() + " et le password " + employee.getPassword() + " ne match pas.";
//    }

    public void showMessage() {
        popUp.showError(typeError, message);
    }
}
