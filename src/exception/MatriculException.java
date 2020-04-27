package exception;

import view.PopUp;

public class MatriculException extends Throwable {
    private static final String typeError = "Input Error";
    private static final String message = "Your matricule can only contains numbers.";

    public MatriculException () {
    }

    public void showMessage() {
        PopUp.showError(typeError, message);
    }
}
