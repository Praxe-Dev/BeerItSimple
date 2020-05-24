package exception;

public class DataQueryException extends Exception {
    private final String typeError = "Data error";
    private final String message = "We couldn't retrieve the data. Please refresh the page and then retry.";

    public String getTypeError() {
        return typeError;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
