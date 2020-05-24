package exception;

import utils.PopUp;

public class CustomerException extends Exception {
    private static String typeError;
    private static String message;

    public CustomerException(String message, String typeError){
        this.message = message;
        this.typeError = typeError;
    }

    public CustomerException(String message){
        this(message, "Error");
    }

    public CustomerException(){
        this("Create error", "Error");
    }

    public void showMessage() {
        switch (typeError) {
            case "Error":
                PopUp.showError(typeError, message);
                break;
            case "Info":
                PopUp.showInfo(typeError, message);
                break;
            case "Success":
                PopUp.showSuccess(typeError, message);
                break;
        }
    }
}
