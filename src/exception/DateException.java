package exception;

import view.PopUp;

public class DateException extends Exception {
    private final String typeError = "Wrong date";
    private final String message = "The end date should be today or before and the start date should be equals to end date or before !";

    public DateException() {
        PopUp.showError(typeError, message);
    }
}
