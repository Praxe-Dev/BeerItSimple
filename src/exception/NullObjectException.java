package exception;

public class NullObjectException extends Exception {
    private String currentObject;

    public NullObjectException(String currentObject) {
        this.currentObject = currentObject;
    }

    @Override
    public String getMessage() {
        return "The object " + currentObject + " is null and should not be.";
    }
}
