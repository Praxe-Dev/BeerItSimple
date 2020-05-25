package exception;

import utils.PopUp;

public class DeletionExceiption extends BISException {
    private String typeError = "Deletion issue";
    private String message = "An issue has occured while deleting.";

    public String getTypeError() {
        return typeError;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
