package view;

import com.jfoenix.controls.JFXButton;
import controller.CustomerController;
import exception.CustomerException;
import exception.NoCustomerFoundException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import model.Customer;
import model.CustomerTableFormat;
import view.customer.Update;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CustomersView extends View implements Initializable {

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

        init();
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

        updateTable();

        // Permet de redimensionner les colonnes lorsque la taille de la fenêtre change
        customersTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        for (int i = 0; i < customersTable.getColumns().size(); i++) {
            customersTable.getColumns().get(i).prefWidthProperty().bind(customersTable.widthProperty().multiply((double) 1 / customersTable.getColumns().size()));
        }
    }

    @Override
    public void init() {
        newCustomer.setOnAction(e -> {
            Window login = new Window("FXML/customer/create.fxml", "New Customer");
            login.load();
            login.getView().setParentView(this);
            login.resizable(false);
            login.show();
        });

        editCustomer.setOnAction(e -> {
            Window editCustomer = new Window("FXML/customer/update.fxml", "BeerItSimple - Edit customer");

            editCustomer.load();
            editCustomer.resizable(false);
            editCustomer.getView().setParentView(this);
            // assurément Update car on le crée nous même juste avant
            Update Update = (Update) editCustomer.getView();
            Customer customer = null;
            try {
                customer = getSelectedCustomer();
            } catch (Exception exception) {
                PopUp.showError("Customer not found", "You may be didn't selected a customer in the table.");
            }

            if (customer != null) {
                Update.setCustomer(customer);
                editCustomer.show();
            } else {
                editCustomer.close();
            }
        });
    }

    private Customer getSelectedCustomer() throws CustomerException, NoCustomerFoundException {
        CustomerTableFormat customerTableFormat = customersTable.getSelectionModel().getSelectedItem();
        Customer customer = null;

        customer = customersController.getCustomer(customerTableFormat.getId());
        return customer;
    }

    public void updateTable() {
        // Transforme les customers en CustomerTableFormat pour l'affichage
        ArrayList<Customer> customersList = customersController.getAllCustomers();
        ArrayList<CustomerTableFormat> customersRow = new ArrayList<>();
        for (Customer customer : customersList) {
            customersRow.add(new CustomerTableFormat(customer));
        }

        customersTable.getItems().setAll(customersRow);
    }
}
