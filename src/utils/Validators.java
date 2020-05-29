package utils;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import com.jfoenix.validation.RegexValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;

public class Validators {

    public static void setMailValidators(JFXTextField mail) {
        //String regexPattern = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        String regexPattern = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)(\\.([-a-zA-Z]{2,10})){1,2}$";
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

    public static void setAddressValidator(JFXTextField field) {
        //String regexPattern = "^([a-zA-Z-À-ÿ']+\\s)*[-a-zA-Z-À-ÿ']+$";
        String regexPattern = "^[A-z]+((\\s){1}([A-z]+|[-'À-ÿ]+)+){1,}$";
        RegexValidator validator = new RegexValidator();
        validator.setRegexPattern(regexPattern);
        validator.setMessage("Address should not contains numbers and special characters");

        field.getValidators().add(validator);
        setReqField(field);
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

    public static void setReqField(JFXPasswordField field) {
        RequiredFieldValidator reqField = new RequiredFieldValidator();
        reqField.setMessage("Required field");

        field.getValidators().add(0, reqField);

        addListener(field);
    }

    public static void setReqField(JFXTextField field) {
        RequiredFieldValidator reqField = new RequiredFieldValidator();
        reqField.setMessage("Required field");

        field.getValidators().add(0, reqField);

        addListener(field);
    }

    public static void setReqField(JFXTextArea area) {
        RequiredFieldValidator reqField = new RequiredFieldValidator();
        reqField.setMessage("Required field");

        area.getValidators().add(0, reqField);

        addListener(area);
    }

    public static void setReqField(JFXTimePicker time) {
        RequiredFieldValidator reqField = new RequiredFieldValidator();
        reqField.setMessage("Required field");

        time.getValidators().add(0, reqField);

        addListener(time);
    }

    public static void setTextAndNumberValidator(JFXTextField field){
        String regexPattern = "^[A-Za-z0-9]+";
        String message = "Should only contains characters and number";
        RegexValidator fieldValidator = new RegexValidator();
        fieldValidator.setRegexPattern(regexPattern);
        fieldValidator.setMessage(message);

        field.getValidators().add(fieldValidator);
        addListener(field);
    }

    public static void setTextAndNumberValidator(JFXTextArea area){
        String regexPattern = "^[A-Za-z0-9]+";
        String message = "Should only contains characters and number";
        RegexValidator fieldValidator = new RegexValidator();
        fieldValidator.setRegexPattern(regexPattern);
        fieldValidator.setMessage(message);

        area.getValidators().add(fieldValidator);
        addListener(area);
    }

    public static void setNumberValidator(JFXTextField field) {
        String regexPattern = "(^[1-9])[0-9]{0,3}";
        String message = ">= 1";
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

    private static void addListener(JFXPasswordField field) {
        field.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal && !newVal.equals("")) {
                field.validate();
            }
        });
    }

    private static void addListener(JFXTextArea area) {
        area.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal && !newVal.equals("")) {
                area.validate();
            }
        });
    }

    private static void addListener(JFXTimePicker time) {
        time.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal && !newVal.equals("")) {
                time.validate();
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

    public static boolean startingDateIsBeforeNow(LocalDate start){
        return start.isBefore(LocalDate.now());
    }

    public static boolean endIsAfterStart(LocalDate start, LocalDate end){
        return start.isBefore(end.plusDays(1));
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

    public static boolean validate(JFXTextArea... elements) {
        boolean check = true;
        for (JFXTextArea element : elements) {
            if (element.getText() != null) {
                if (!element.validate()) {
                    check = false;
                }
            }
        }

        return check;
    }

    public static boolean validate(JFXTimePicker... elements) {
        boolean check = true;
        for (JFXTimePicker element : elements) {
            if (element.getValue() == null) {
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
