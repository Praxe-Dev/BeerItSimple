package exception;

import model.Customer;

public class InsertionError extends Exception {
    private Customer customer;
    private final String typeError = "Insertion error";

    public InsertionError(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String getMessage() {
        return "An error occured while we were trying to insert the object.\n";
    }

    public String getType() {
    return this.typeError;
    }
}
