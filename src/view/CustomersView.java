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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

public class CustomersView extends VBox implements Initializable {

    @FXML
    private VBox vbox;
    @FXML
    private Label label;
    @FXML
    private JFXButton newCustomer;
    @FXML
    private TableView<CustomerTableFormat> customersTable;
    @FXML
    private TableColumn<CustomerTableFormat, Integer> customerId;
    @FXML
    private TableColumn<CustomerTableFormat, String> contactName;
    @FXML
    private TableColumn<CustomerTableFormat, String> phoneNumber;
    @FXML
    private TableColumn<CustomerTableFormat, String> address;
    @FXML
    private TableColumn<CustomerTableFormat, String> subscriptionDate;
    @FXML
    private TableColumn<CustomerTableFormat, String> bankAccountNumber;
    @FXML
    private TableColumn<CustomerTableFormat, String> businessNumber;
    @FXML
    private TableColumn<CustomerTableFormat, String> VATNumber;
    @FXML
    private TableColumn<CustomerTableFormat, String> cityLabel;
    @FXML
    private TableColumn<CustomerTableFormat, Integer> zipCode;
    @FXML
    private TableColumn<CustomerTableFormat, String> rankLabel;
    @FXML
    private TableColumn<CustomerTableFormat, Integer> creditLimit;

    /**
     * Variable d'instance
     */
    private CustomerController customersController;

    public CustomersView() {
        this.customersController = new CustomerController();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        newCustomer.setOnAction(e -> {
            System.out.print("Try Open create customer");
            Window login = new Window("FXML/newCustomerPanel.fxml", "New Customer");
            login.load();
            login.resizable(false);
//            login.undecorated();
            login.show();
        });

        initTableCustomer();

    }

    public void initTableCustomer() {
        customerId.setCellValueFactory(new PropertyValueFactory<CustomerTableFormat, Integer>("id"));
        contactName.setCellValueFactory(new PropertyValueFactory<CustomerTableFormat, String>("contactName"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<CustomerTableFormat, String>("phoneNumber"));
        address.setCellValueFactory(new PropertyValueFactory<CustomerTableFormat, String>("address"));
//        subscriptionDate.setCellValueFactory(new PropertyValueFactory<CustomerTableFormat, String>("subscriptionDate"));
        bankAccountNumber.setCellValueFactory(new PropertyValueFactory<CustomerTableFormat, String>("bankAccountNumber"));
        businessNumber.setCellValueFactory(new PropertyValueFactory<CustomerTableFormat, String>("businessNumber"));
        VATNumber.setCellValueFactory(new PropertyValueFactory<CustomerTableFormat, String>("VATNumber"));
        cityLabel.setCellValueFactory(new PropertyValueFactory<CustomerTableFormat, String>("cityLabel"));
        zipCode.setCellValueFactory(new PropertyValueFactory<CustomerTableFormat, Integer>("zipCode"));
        rankLabel.setCellValueFactory(new PropertyValueFactory<CustomerTableFormat, String>("rankLabel"));
        creditLimit.setCellValueFactory(new PropertyValueFactory<CustomerTableFormat, Integer>("creditLimit"));

        ArrayList<Customer> customersList = customersController.getAllCustomers();
        ArrayList<CustomerTableFormat> customersRow = new ArrayList<>();
        for (Customer customer : customersList) {
            customersRow.add(new CustomerTableFormat(customer));
        }

        customersTable.getItems().setAll(customersRow);

        customersTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//        System.out.println("Multiple : " + (double) 1 /customersTable.getColumns().size());
        for (int i = 0; i < customersTable.getColumns().size(); i++) {
            customersTable.getColumns().get(i).prefWidthProperty().bind(customersTable.widthProperty().multiply((double) 1 / customersTable.getColumns().size()));
        }
    }
}
