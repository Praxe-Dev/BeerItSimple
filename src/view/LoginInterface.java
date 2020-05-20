package view;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.util.Locale;

public class LoginInterface extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Lance la fenêtre de login au lancement du programme
     */
    @Override
    public void start(Stage stage) {
        Window login = new Window("FXML/loginPanel.fxml", "Login");
        Document doc = new Document();

//        Locale.setDefault(Locale.ENGLISH);
        login.load();
        login.show();
    }
}



