package view.customer;

import com.jfoenix.controls.JFXButton;
import controller.CustomerController;
import exception.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import model.CustomerTableFormat;
import utils.PopUp;
import view.View;
import view.Window;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Index extends View implements Initializable {
    @FXML
    private JFXButton refreshBtn;
    @FXML
    private JFXButton newCustomer;
    @FXML
    private JFXButton editCustomer;
    @FXML
    private JFXButton detailBtn;
    @FXML
    private TableView<CustomerTableFormat> customersTable;
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
    @FXML
    private JFXButton deleteBtn;

    private CustomerController customersController;

    public Index() {
        try {
            this.customersController = new CustomerController();
        } catch (ConnectionException e) {
            showError(e.getTypeError(), e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshBtn.setOnAction(e -> updateTable());
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

        detailBtn.setOnAction(e -> {
            Window readCustomer = new Window("FXML/customer/read.fxml", "BeerItSimple - Details");

            readCustomer.load();
            readCustomer.resizable(false);
            readCustomer.getView().setParentView(this);

            Read Read = (Read) readCustomer.getView();
            Customer customer = null;
            try {
                customer = getSelectedCustomer();
            } catch (Exception exception) {
                PopUp.showError("Customer not found", "You may be didn't selected a customer in the table.");
            }

            if (customer != null) {
                Read.setCustomer(customer);
                readCustomer.show();
            } else {
                readCustomer.close();
            }
        });

        deleteBtn.setOnAction(e -> {
            Customer customer = getSelectedCustomer();

            if (customer != null && PopUp.showConfirm("Confirm delete", "Are you sur you want to delete " + customer.getEntity().getContactName() + " ?")) {
                try {
                    if (customersController.delete(customer)) {
                        updateTable();
                    }
                } catch (DeletionException ex) {
                    showError(ex.getTypeError(), ex.getMessage());
                }
            }
        });
    }

    private Customer getSelectedCustomer() {
        Customer customer = null;

        try {
            CustomerTableFormat customerTableFormat = customersTable.getSelectionModel().getSelectedItem();
            if (customerTableFormat == null)
                throw new NoRowSelected();

            customer = customersController.getCustomer(customerTableFormat.getId());
        } catch (DataQueryException | NoRowSelected e) {
            showError(e.getTypeError(), e.getMessage());
        }


        return customer;
    }

    public void updateTable() {
        // Transforme les customers en CustomerTableFormat pour l'affichage
        ArrayList<Customer> customersList = null;
        try {
            customersList = customersController.getAllCustomers();
        } catch (DataQueryException e) {
            showError(e.getTypeError(), e.getMessage());
        }
        ArrayList<CustomerTableFormat> customersRow = new ArrayList<>();
        for (Customer customer : customersList) {
            customersRow.add(new CustomerTableFormat(customer));
        }

        customersTable.getItems().setAll(customersRow);
    }
}
