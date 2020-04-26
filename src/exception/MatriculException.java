package exception;

import view.popUp;

public class MatriculException extends Throwable {
    private static final String typeError = "Input Error";
    private static final String message = "Your matricule can only contains numbers.";

    public MatriculException () {
    }

    public void showMessage() {
        popUp.showError(typeError, message);
    }
}
