package view;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeView extends View implements Initializable {
    @FXML
    private JFXButton incomeBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }

    @Override
    public void init() {
        incomeBtn.setOnAction(e -> {
            openIncomePanel();
        });
    }

    private void openIncomePanel() {
        Window income = new Window("FXML/businessTask/incomePanel.fxml", "BeerItSimple - Income");
        income.load();
        income.show();
    }
}
