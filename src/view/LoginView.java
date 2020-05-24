package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import controller.EmployeeController;
import exception.EmployeeLoginException;
import exception.MatriculException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Employee;

public class LoginView extends View {
    @FXML
    private VBox loginContainer;
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
        signinButton.setOnAction(e -> {
            // TODO: Tenter de se connecter
            openSession();
        });

        setShortcut(new KeyCodeCombination(KeyCode.ENTER), () -> openSession());

        employeeController = new EmployeeController();
        makeDraggable(loginContainer);
    }

    private void openSession() {

        try {
            int matricule = getMatricule();
            String password = getPassword();

            // Can save employee as user in view.Window to manage permission
            Employee employee = employeeController.getEmployee(matricule, password);
            Window session;
            session = new Window(PathToMainPanel, "BeerItSimple - Home");

            switchWindow(session);

            MainView mainView = (MainView) session.getView();
            //TODO: Le modèle employé doit contenir une référence entité. La requete de connexion doit être adaptée.
            mainView.setUsername(employee.getEntity().getContactName());
            mainView.setRole(employee.getRole());
            //mainView.setUsername("Administrator");
        } catch (EmployeeLoginException exception) {
                exception.showMessage();
        } catch (MatriculException exception) {
                exception.showMessage();
        }
    }

    public int getMatricule() throws MatriculException {
        if (employeeMatricule.getText().matches("\\d+")) {
            return Integer.parseInt(employeeMatricule.getText());
        } else {
            throw new MatriculException();
        }
    }

    public String getPassword() {
        return employeePassword.getText();
    }
}
