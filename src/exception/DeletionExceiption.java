package exception;

import utils.PopUp;

public class DeletionExceiption extends Exception {
    private static String typeError = "Deletion issue";
    private static String message = "An issue has occured while deleting.";

    public static String getTypeError() {
        return typeError;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
