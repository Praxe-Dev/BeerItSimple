package exception;

import utils.PopUp;

public class UpdateException extends Exception{
    private final String typeError = "Update error";
    private final String message = "The update failed, retry later.";

    public String getTypeError() {
        return typeError;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
