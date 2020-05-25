package exception;

import model.Customer;

public class CustomerUpdateException extends Exception {
    private Customer customer;
    private final String typeError = "Customer update error";
    private final String message = "An error occured while we were trying to update the entity ";

    public CustomerUpdateException(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String getMessage() {
        return message + customer;
    }

    public String getTypeError() {
        return typeError;
    }
}
