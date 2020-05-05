package exception;

import view.PopUp;

public class NoRowSelected extends Exception {
    private final String errorType = "No row selected";
    private final String message = "You should have select one row to perform this action";

    public NoRowSelected() {
        showError();
    }

    public void showError() {
        PopUp.showError(errorType, message);
    }
}
