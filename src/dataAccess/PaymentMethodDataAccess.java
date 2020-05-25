package dataAccess;

import exception.DataQueryException;
import model.PaymentMethod;

import java.util.ArrayList;

public interface PaymentMethodDataAccess {
    ArrayList<PaymentMethod> getAllPaymentMethod() throws DataQueryException;
}
