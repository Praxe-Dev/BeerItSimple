package exception;

public class ConnectionException extends Exception {
    private final String typeError = "Connection Error";
    private final String message = "An error occured while we tried to reach the data base.\nTry to verify your connexion if the error persist.";

    public String getTypeError() {
        return typeError;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
