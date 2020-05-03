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

    public static void showInfo(String type, String message) {
        Alert error = new Alert(Alert.AlertType.INFORMATION);
        error.setTitle("Information");
        error.setHeaderText(type);
        error.setContentText(message);
        error.showAndWait();
    }

    public static void showSuccess(String type, String message) {
        Alert error = new Alert(Alert.AlertType.INFORMATION);
        error.setTitle("Success");
        error.setHeaderText(type);
        error.setContentText(message);
        error.showAndWait();
    }
}
