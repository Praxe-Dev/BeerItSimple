package exception;

import utils.PopUp;

public class DeletionExceiption extends Throwable {
    private static String typeError = "Deletion issue";
    private static String message = "An issue has occured while deleting.";

    public DeletionExceiption() { };

    public void showError() {
        PopUp.showError(typeError, message);
    }
}
