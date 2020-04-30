package exception;

import view.PopUp;

import java.awt.*;
import java.sql.SQLException;

public class SQLManageException {
    private static SQLException exception;

    public SQLManageException(SQLException exception){
        this.exception = exception;
    }

    public void showMessage(){
        PopUp.showError("Erreur..", exception.getMessage());
    }
}
