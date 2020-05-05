package exception;

import view.PopUp;

public class PaymentMethodException extends Throwable {
    private static final String typeError = "Payment method error";
    private static final String message = "You can't change payment method after order is paid.";

    public PaymentMethodException () {
    }

    public void showMessage() {
        PopUp.showError(typeError, message);
    }
}
