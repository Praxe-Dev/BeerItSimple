package exception;

public class BISException extends Exception {
    private String typeError = "Error";
    private String message = "An error occured.";

    public BISException(String typeError, String message) {
        setTypeError(typeError);
        setMessage(message);
    }

    public void setTypeError(String typeError) {
        if (typeError != null)
            this.typeError = typeError;
    }

    public void setMessage(String message) {
        if (message != null)
            this.message = message;
    }

    public String getTypeError() {
        return typeError;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
