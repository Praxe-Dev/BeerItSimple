package exception;

import utils.PopUp;

import java.sql.SQLException;

public class ThreadNewsException extends BISException {
    private final String errorType = "Thread error";
    private final String message = "You have an error with the thread to get random news.";

    @Override
    public String getTypeError() {
        return errorType;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void showMessage(){
        PopUp.showError(errorType, message);
    }
}
