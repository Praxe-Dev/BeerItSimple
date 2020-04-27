package exception;

import model.Employee;
import view.PopUp;

public class EmployeeLoginException extends Exception {
    private Employee employee;
    private final static String typeError = "Connection Error";
    private final static String message = "Login and password doesn't match.";

    public void showMessage() {
        PopUp.showError(typeError, message);
    }
}
