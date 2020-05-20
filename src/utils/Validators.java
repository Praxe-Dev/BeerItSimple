package utils;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RegexValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.scene.Node;

import java.time.LocalDate;

public class Validators {

    public static void setMailValidators(JFXTextField mail) {
        String regexPattern = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        String message = "Format sould be : abc@domain.eu";

        RegexValidator mailValidator = new RegexValidator();
        mailValidator.setRegexPattern(regexPattern);
        mailValidator.setMessage(message);

        mail.getValidators().add(mailValidator);
        addListenerOptional(mail);
    }

    public static void setPhoneNumberValidator(JFXTextField phoneNumber) {
        String regexPattern = "^(\\d{4})\\/(\\d{2})\\.(\\d{2})\\.(\\d{2})$";
        String message = "Format should be XXXX/XX.XX.XX";
        RegexValidator phoneNumberValidator = new RegexValidator();
        phoneNumberValidator.setRegexPattern(regexPattern);
        phoneNumberValidator.setMessage(message);

        phoneNumber.getValidators().add(phoneNumberValidator);
        setReqField(phoneNumber);
    }

    public static void setAddressValidator(JFXTextField address) {
        String regexPattern = "^([a-zA-Z']+\\s)*[a-zA-Z']+$";
        String message = "Address should not contains number and extra space";
        RegexValidator addressValidator = new RegexValidator();
        addressValidator.setRegexPattern(regexPattern);
        addressValidator.setMessage("Address should not contains numbers");

        address.getValidators().add(addressValidator);
        setReqField(address);
    }

    public static void setHouseNumberValidator(JFXTextField houseNumber) {
        String regexPattern = "^[\\d]{0,3}";
        String message = "Should only contains number";
        RegexValidator houseNumberValidator = new RegexValidator();
        houseNumberValidator.setRegexPattern(regexPattern);
        houseNumberValidator.setMessage(message);

        houseNumber.getValidators().add(houseNumberValidator);
        setReqField(houseNumber);
    }

    public static void setAccountNumberValidator(JFXTextField accountNumber) {
        String regexPattern = "^([A-Z]{2})(\\d{14})$";
        String message = "Format : BEXXXXXXXXXXXXXX";
        RegexValidator accountNumberValidator = new RegexValidator();
        accountNumberValidator.setRegexPattern(regexPattern);
        accountNumberValidator.setMessage(message);

        accountNumber.getValidators().add(accountNumberValidator);
        addListenerOptional(accountNumber);
    }

    public static void setBusinessNumberValidator(JFXTextField businessNumber) {
        String regexPattern = "^(\\d{4})\\.(\\d{3})\\.(\\d{3})$";
        String message = "Format : XXXX.XXX.XXX\"";
        RegexValidator businessNumberValidator = new RegexValidator();
        businessNumberValidator.setRegexPattern(regexPattern);
        businessNumberValidator.setMessage(message);

        businessNumber.getValidators().add(businessNumberValidator);
        addListener(businessNumber);
    }

    public static void setReqField(JFXTextField field) {
        RequiredFieldValidator reqField = new RequiredFieldValidator();
        reqField.setMessage("Required field");

        field.getValidators().add(0, reqField);

        addListener(field);
    }

    public static void setNumberValidator(JFXTextField field) {
        String regexPattern = "(^[1-9])[0-9]{0,3}";
        String message = "Should only contains number";
        RegexValidator fieldValidator = new RegexValidator();
        fieldValidator.setRegexPattern(regexPattern);
        fieldValidator.setMessage(message);

        field.getValidators().add(fieldValidator);
        addListener(field);
    }

    private static void addListener(JFXTextField field) {
        field.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal && !newVal.equals("")) {
                field.validate();
            }
        });
    }

    private static void addListenerOptional(JFXTextField field) {
        field.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!field.getText().equals("")) {
                field.validate();
            }
        });
    }

    public static boolean validateBetweenDates(LocalDate start, LocalDate end) {
        return end.isBefore(LocalDate.now().plusDays(1)) && start.isBefore(end.plusDays(1));
    }

    public static boolean validate(JFXTextField... elements) {
        boolean check = true;
        for (JFXTextField element : elements) {
            if (element.getText() != null) {
                if (!element.validate()) {
                    check = false;
                }
            }
        }

        return check;
    }

    public static boolean validateNullableValue(JFXTextField... elements) {
//        boolean check = true;
        for (JFXTextField element : elements) {
            if (!element.getText().equals("")) {
                if (!element.validate()) {
                    return false;
                }
            }
        }

        return true;
    }
}
