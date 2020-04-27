package view;

import controller.CustomerController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import model.CustomerTableView;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomersView extends VBox implements Initializable {

    @FXML
    private VBox vbox;
    @FXML
    private Label label;

    /**
     * Variable d'instance
     */
    private CustomerController customersController;

    public CustomersView() {
        this.customersController = new CustomerController();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        TableView<CustomerTableView> customersTable = new TableView<>();

        ObservableList<CustomerTableView> allCustomer = customersController.getAllCustomers();

        TableColumn<CustomerTableView, Integer> idColumn = new TableColumn<CustomerTableView, Integer>("Id #");
        idColumn.prefWidthProperty().bind(customersTable.widthProperty().multiply(0.1));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<CustomerTableView, String> contactNameColumn = new TableColumn<CustomerTableView, String>("Contact Name");
        contactNameColumn.prefWidthProperty().bind(customersTable.widthProperty().multiply(0.3));
        contactNameColumn.setCellValueFactory(new PropertyValueFactory<>("contactName"));

        TableColumn<CustomerTableView, String> phoneNumberColumn = new TableColumn<CustomerTableView, String>("Phone Number");
        phoneNumberColumn.prefWidthProperty().bind(customersTable.widthProperty().multiply(0.3));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        TableColumn<CustomerTableView, String> mailColumn = new TableColumn<CustomerTableView, String>("Mail");
        mailColumn.prefWidthProperty().bind(customersTable.widthProperty().multiply(0.3));
        mailColumn.setCellValueFactory(new PropertyValueFactory<>("mail"));

        customersTable.setItems(customersController.getAllCustomers());
        customersTable.getColumns().addAll(idColumn, contactNameColumn, phoneNumberColumn, mailColumn);

        int labelIndex = vbox.getChildren().indexOf(label);
        vbox.getChildren().add(labelIndex + 1,customersTable);
    }
}