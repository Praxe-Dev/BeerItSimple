package exception;

import utils.PopUp;

public class NoRowSelected extends BISException {
    private final String errorType = "No row selected";
    private final String message = "You should have select one row to perform this action";

    @Override
    public String getTypeError() {
        return null;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
