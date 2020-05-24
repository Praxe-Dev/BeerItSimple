package exception;

import utils.PopUp;

public class UpdateOrderException extends Throwable{
    private static String exception;

    public UpdateOrderException(String exception){
        this.exception = exception;
    }

    public void showMessage(){
        PopUp.showError("Erreur..", exception);
    }
}
