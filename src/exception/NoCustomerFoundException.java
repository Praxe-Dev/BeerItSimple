package exception;

import view.PopUp;

public class NoCustomerFoundException extends Exception {
    private static final String typeError = "No customer found";
    private static final String message =   "No customer has been found, maybe you didn't selected one in the table or we couldn't retrieve this one.\n" +
                                            "Try reload the page.";

    public NoCustomerFoundException() {
    }

    public void showMessage() {
        PopUp.showError(typeError, message);
    }
}
