package exception;

public class CustomerNotFoundException extends Exception {
    private int id;
    private final String typeError = "Entity not found";

    public CustomerNotFoundException(int id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "We couldn't retrieve the entity with the id " + id;
    }

    public String getTypeError () {
        return this.getTypeError();
    }
}
