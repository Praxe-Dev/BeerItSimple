package utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class PopUp {

    public static void showError(String errorType, String message) {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Error");
        error.setHeaderText(errorType);
        error.setContentText(message);
        error.showAndWait();
    }

    public static void showInfo(String type, String message) {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Information");
        info.setHeaderText(type);
        info.setContentText(message);
        info.showAndWait();
    }

    public static void showSuccess(String type, String message) {
        Alert success = new Alert(Alert.AlertType.INFORMATION);
        success.setTitle("Success");
        success.setHeaderText(type);
        success.setContentText(message);
        success.showAndWait();
    }

    public static boolean showConfirm(String type, String message) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setHeaderText(type);
        confirm.setContentText(message);
        Optional<ButtonType> result = confirm.showAndWait();

        if (result.get() == ButtonType.OK){
            return true;
        } else {
            return false;
        }
    }
}
