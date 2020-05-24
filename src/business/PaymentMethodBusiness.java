package business;

import dataAccess.PaymentMethodDBAccess;
import dataAccess.PaymentMethodDataAccess;
import exception.ConnectionException;
import model.PaymentMethod;

import java.util.ArrayList;

public class PaymentMethodBusiness {
    private PaymentMethodDataAccess dao;

    public PaymentMethodBusiness() throws ConnectionException {
        this.dao = new PaymentMethodDBAccess();
    }


    public ArrayList<PaymentMethod> getAllPaymentMethod() {
        return dao.getAllPaymentMethod();
    }
}
