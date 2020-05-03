package controller;

import business.PaymentMethodBusiness;
import model.PaymentMethod;

import java.util.ArrayList;

public class PaymentMethodController {
    private PaymentMethodBusiness paymentMethodBusiness;

    public PaymentMethodController() {
        this.paymentMethodBusiness = new PaymentMethodBusiness();
    }

    public ArrayList<PaymentMethod> getAllPaymentMethod() {
        return paymentMethodBusiness.getAllPaymentMethod();
    }
}
