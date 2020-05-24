package exception;

public abstract class BISException extends Exception {
    public abstract String getTypeError();

    @Override
    public abstract String getMessage();
}
