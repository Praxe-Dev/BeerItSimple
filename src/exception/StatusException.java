package exception;

import utils.PopUp;

public class StatusException extends Throwable {
    private static String exception;

    public StatusException(String exception){
        this.exception = exception;
    }

    public void showMessage(){
        PopUp.showError("Erreur..", exception);
    }
}
