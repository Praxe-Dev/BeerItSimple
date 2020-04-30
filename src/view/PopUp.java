package view;

import javafx.scene.control.Alert;

public class PopUp {

    public static void showError(String errorType, String message) {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Error");
        error.setHeaderText(errorType);
        error.setContentText(message);
        error.showAndWait();
    }

    public static void showInfo(String errorType, String message) {
        Alert error = new Alert(Alert.AlertType.INFORMATION);
        error.setTitle("Information");
        error.setHeaderText(errorType);
        error.setContentText(message);
        error.showAndWait();
    }

    public static void showSuccess(String errorType, String message) {
        Alert error = new Alert(Alert.AlertType.INFORMATION);
        error.setTitle("Succès");
        error.setHeaderText(errorType);
        error.setContentText(message);
        error.showAndWait();
    }
}
