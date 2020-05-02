package view;

import com.jfoenix.controls.JFXButton;
import controller.CustomerController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import model.Customer;
import model.CustomerTableFormat;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CustomersView extends VBox implements Initializable {

    @FXML
    private VBox vbox;
    @FXML
    private Label label;
    @FXML
    private JFXButton newCustomer;
    @FXML
    private JFXButton editCustomer;
    @FXML
    TableView<CustomerTableFormat> customersTable;
    @FXML
    private TableColumn<CustomerTableFormat, Integer> customerId;
    @FXML
    private TableColumn<CustomerTableFormat, String> contactName;
    @FXML
    private TableColumn<CustomerTableFormat, String> phoneNumber;
    @FXML
    private TableColumn<CustomerTableFormat, String> mail;
    @FXML
    private TableColumn<CustomerTableFormat, String> address;
    @FXML
    private TableColumn<CustomerTableFormat, String> subscriptionDate;
    @FXML
    private TableColumn<CustomerTableFormat, String> bankAccountNumber;
    @FXML
    private TableColumn<CustomerTableFormat, String> businessNumber;
    @FXML
    private TableColumn<CustomerTableFormat, String> cityLabel;
    @FXML
    private TableColumn<CustomerTableFormat, Integer> zipCode;
    @FXML
    private TableColumn<CustomerTableFormat, String> rankLabel;
    @FXML
    private TableColumn<CustomerTableFormat, Integer> creditLimit;

    private CustomerController customersController;

    public CustomersView() {
        this.customersController = new CustomerController();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        newCustomer.setOnAction(e -> {
            Window login = new Window("FXML/newCustomerPanel.fxml", "New Customer");
            login.load();
            login.resizable(false);
            login.show();
        });

        editCustomer.setOnAction(e -> {
            Window editCustomer = new Window("FXML/editCustomerPanel.fxml", "BeerItSimple - Edit customer");
            editCustomer.load();
            editCustomer.resizable(false);
//            editCustomer.getView().setParentView(this);
        });

        initTableCustomer();
    }

    public void initTableCustomer() {
        // Add the factory to the cell
        // That allow the cell to retrieve its data and display it
        customerId.setCellValueFactory(new PropertyValueFactory<CustomerTableFormat, Integer>("id"));
        contactName.setCellValueFactory(new PropertyValueFactory<CustomerTableFormat, String>("contactName"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<CustomerTableFormat, String>("phoneNumber"));
        mail.setCellValueFactory(new PropertyValueFactory<CustomerTableFormat, String>("mail"));
        address.setCellValueFactory(new PropertyValueFactory<CustomerTableFormat, String>("address"));
        subscriptionDate.setCellValueFactory(new PropertyValueFactory<CustomerTableFormat, String>("subscriptionDate"));
        bankAccountNumber.setCellValueFactory(new PropertyValueFactory<CustomerTableFormat, String>("bankAccountNumber"));
        businessNumber.setCellValueFactory(new PropertyValueFactory<CustomerTableFormat, String>("businessNumber"));
        cityLabel.setCellValueFactory(new PropertyValueFactory<CustomerTableFormat, String>("cityLabel"));
        zipCode.setCellValueFactory(new PropertyValueFactory<CustomerTableFormat, Integer>("zipCode"));
        rankLabel.setCellValueFactory(new PropertyValueFactory<CustomerTableFormat, String>("rankLabel"));
        creditLimit.setCellValueFactory(new PropertyValueFactory<CustomerTableFormat, Integer>("creditLimit"));

        // Transforme les customers en CustomerTableFormat pour l'affichage
        ArrayList<Customer> customersList = customersController.getAllCustomers();
        ArrayList<CustomerTableFormat> customersRow = new ArrayList<>();
        for (Customer customer : customersList) {
            customersRow.add(new CustomerTableFormat(customer));
        }

        customersTable.getItems().setAll(customersRow);

        // Permet de redimensionner les colonnes lorsque la taille de la fenêtre change
        customersTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        for (int i = 0; i < customersTable.getColumns().size(); i++) {
            customersTable.getColumns().get(i).prefWidthProperty().bind(customersTable.widthProperty().multiply((double) 1 / customersTable.getColumns().size()));
        }
    }

//    protected Customer getSelectedCustomer() {
//        CustomerTableFormat customerTableFormat = customersTable.getSelectionModel().getSelectedItem();
//        Customer customer = customersController.getCustomer(customerTableFormat.getId());
//
//        return customer;
//    }
}
