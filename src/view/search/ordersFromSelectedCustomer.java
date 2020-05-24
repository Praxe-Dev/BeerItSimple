package view.search;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import controller.CustomerController;
import controller.OrderController;
import exception.SQLManageException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.Customer;
import model.Order;
import utils.PopUp;
import view.View;
import view.Window;
import view.order.Index;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ordersFromSelectedCustomer extends View implements Initializable {
    @FXML
    JFXComboBox<Customer> customerBox;
    @FXML
    JFXButton cancelBtn;
    @FXML
    JFXButton searchBtn;

    CustomerController customerController;
    OrderController orderController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }

    @Override
    public void init() {
        customerController = new CustomerController();
        orderController = new OrderController();
        //Init customerBox
        ArrayList<Customer> allCustomers = customerController.getAllCustomers();
        customerBox.setItems(FXCollections.observableArrayList(allCustomers));
        customerBox.getSelectionModel().selectFirst();
        customerBox.getStyleClass().add("whiteComboBox");

        cancelBtn.setOnAction(e -> {
            closeWindow();
        });

        searchBtn.setOnAction(e -> {
            search();
        });
    }

    private void search(){
        //Create view with get customer
        try {
            Customer customer = customerBox.getSelectionModel().getSelectedItem();
            ArrayList<Order> allOrders = orderController.getAllOrdersFromCustomer(customer);
            if(allOrders == null || allOrders.size() == 0){
                PopUp.showError("No order", "This customer doesn't have an order yet.");
            } else {
                openNewTabView(allOrders, customer);
            }
        } catch(SQLManageException e){
            e.showMessage();
        }

    }

    private void openNewTabView(ArrayList<Order> allOrdersFromCustomer, Customer customer) {
        Window displayResult = new Window("FXML/order/index.fxml", "BeerItSimple - All orders from selected customer : " + customer.getEntity().getContactName() + " (" + customer.getEntity().getPhoneNumber() + ")");
        displayResult.load();
        displayResult.getView().setParentView(this);
        Index index = (Index) displayResult.getView();
        index.updateTable(allOrdersFromCustomer);
        index.hideRefreshButton();
        index.setCustomer(customer);
        index.getMainContainer().setStyle("-fx-background-color: linear-gradient(to left, #0f2027, #203a43, #2c5364)");
        displayResult.show();
    }
}
