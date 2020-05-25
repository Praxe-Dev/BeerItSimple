package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import controller.EmployeeController;
import exception.ConnectionException;
import exception.LoginException;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import model.Employee;
import utils.Validators;

public class LoginView extends View {
    @FXML
    private JFXTextField employeeMatricule;
    @FXML
    private JFXPasswordField employeePassword;
    @FXML
    private JFXButton signinButton;

    private static final String PathToMainPanel = "FXML/MainPanel.fxml";
    private EmployeeController employeeController;

    @Override
    public void init() {
        Validators.setNumberValidator(employeeMatricule);
        Validators.setReqField(employeeMatricule);

        this.getStage().setOnCloseRequest(e -> {
            System.exit(0);
        });

        try {
            employeeController = new EmployeeController();
        } catch (ConnectionException e) {
            showError(e.getTypeError(), e.getMessage());
        }

        signinButton.setOnAction(e -> {
            // TODO: Tenter de se connecter
            openSession();
        });

        setShortcut(new KeyCodeCombination(KeyCode.ENTER), () -> openSession());
    }

    private void openSession() {

        try {
            int matricule = -1;
            if (employeeMatricule.validate())
                matricule = getMatricule();
            String password = getPassword();

            Employee employee = employeeController.getEmployee(matricule, password);
            Window session;
            session = new Window(PathToMainPanel, "BeerItSimple - Home");
            session.setCurrentUser(employee);
            switchWindow(session);

        } catch (LoginException exception) {
            showError(exception.getTypeError(), exception.getMessage());
        }
    }

    public int getMatricule() {
        return Integer.parseInt(employeeMatricule.getText());
    }

    public String getPassword() {
        return employeePassword.getText();
    }
}
