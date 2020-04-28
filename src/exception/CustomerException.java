package exception;

import view.PopUp;

public class CustomerException extends Exception {
    private final static String typeError = "Customer Error";
    private final static String message = "Create error";

    public void showMessage() {
        PopUp.showError(typeError, message);
    }
}
