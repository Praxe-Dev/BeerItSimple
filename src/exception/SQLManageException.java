package exception;

import view.PopUp;

import java.awt.*;
import java.sql.SQLException;

public class SQLManageException extends Exception {
    private static SQLException exception;

    public SQLManageException(SQLException exception){
        this.exception = exception;
    }

    public void showMessage(){
        PopUp.showError("Erreur..", exception.getMessage());
    }
}
