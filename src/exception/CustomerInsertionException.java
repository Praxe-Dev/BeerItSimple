package exception;

import model.Customer;

public class CustomerInsertionException extends Exception {
    private Customer customer;
    private final String typeError = "Insertion error";

    public CustomerInsertionException(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String getMessage() {
        return "An error occured while we were trying to insert the object " + customer.toString();
    }

    public String getTypeError() {
        return this.typeError;
    }
}
