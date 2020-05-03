package exception;

import view.PopUp;

public class NoRowSelected extends Exception {
    private final String errorType = "No row selected";
    private final String message = "You should select one row to do this action";

    public NoRowSelected() {
        showError();
    }

    public void showError() {
        PopUp.showError(errorType, message);
    }
}
