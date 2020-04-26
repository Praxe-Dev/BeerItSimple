package view;

import javafx.scene.control.Alert;

public class popUp {

    public static void showError(String errorType, String message) {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Error");
        error.setHeaderText(errorType);
        error.setContentText(message);
        error.showAndWait();
    }
}
