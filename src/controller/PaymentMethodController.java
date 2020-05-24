package controller;

import business.PaymentMethodBusiness;
import exception.ConnectionException;
import model.PaymentMethod;

import java.util.ArrayList;

public class PaymentMethodController {
    private PaymentMethodBusiness paymentMethodBusiness;

    public PaymentMethodController() throws ConnectionException {
        this.paymentMethodBusiness = new PaymentMethodBusiness();
    }

    public ArrayList<PaymentMethod> getAllPaymentMethod() {
        return paymentMethodBusiness.getAllPaymentMethod();
    }
}
