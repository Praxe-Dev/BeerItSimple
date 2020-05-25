package exception;

public class DeletionException extends BISException {
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
