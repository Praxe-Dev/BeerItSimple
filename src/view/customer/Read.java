package view.customer;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import controller.OrderController;
import exception.ConnectionException;
import exception.DataQueryException;
import javafx.fxml.FXML;
import javafx.scene.Group;
import model.Customer;
import model.Order;
import utils.PopUp;
import view.View;
import view.Window;
import view.order.Index;

import java.util.ArrayList;


public class Read extends View {
    @FXML
    private JFXRadioButton privateCustomer;
    @FXML
    private JFXRadioButton businessCustomer;
    @FXML
    private JFXTextField customerRank;
    @FXML
    private JFXTextField contactName;
    @FXML
    private JFXTextField phoneNumber;
    @FXML
    private JFXTextField mail;
    @FXML
    private JFXTextField houseNumber;
    @FXML
    private JFXTextField region;
    @FXML
    private JFXTextField address;
    @FXML
    private JFXTextField accountNumber;
    @FXML
    private JFXTextField businessNumber;
    @FXML
    private Group businessView;
    @FXML
    private JFXButton closeBtn;
    @FXML
    private JFXButton viewOrdersBtn;

    private Customer selectedCustomer;
    private OrderController orderController;


    @Override
    public void init() {
        try {
            orderController = new OrderController();
        } catch (ConnectionException e) {
            showError(e.getTypeError(), e.getMessage());
        }

        contactName.setText(selectedCustomer.getEntity().getContactName());
        address.setText(selectedCustomer.getEntity().getStreet());
        houseNumber.setText(selectedCustomer.getEntity().getHouseNumber().toString());
        phoneNumber.setText(selectedCustomer.getEntity().getPhoneNumber());
        region.setText(selectedCustomer.getEntity().getCity().getZipCode().toString());
        customerRank.setText(selectedCustomer.getRank().toString());

        if (selectedCustomer.getEntity().getMail() == null) {
            mail.setText("");
        } else {
            mail.setText(selectedCustomer.getEntity().getMail());
        }

        if (selectedCustomer.getEntity().getBankAccountNumber() == null) {
            accountNumber.setText("");
        } else {
            accountNumber.setText(selectedCustomer.getEntity().getBankAccountNumber());
        }

        if (selectedCustomer.getEntity().getBusinessNumber() == null) {
            businessNumber.setText("");
        } else {
            businessNumber.setText(selectedCustomer.getEntity().getBusinessNumber());
        }

        if (selectedCustomer.getEntity().getBusinessNumber() != null || selectedCustomer.getEntity().getBankAccountNumber() != null) {
            businessView.setVisible(true);
            businessCustomer.setSelected(true);

        } else {
            businessView.setVisible(false);
            privateCustomer.setSelected(true);
        }



        closeBtn.setOnAction(e -> {
            closeWindow();
        });

        viewOrdersBtn.setOnAction(e -> {
            try {
                ArrayList<Order> allOrders = orderController.getAllOrdersFromCustomer(selectedCustomer);
                if (allOrders == null || allOrders.size() == 0) {
                    PopUp.showError("No order", "This customer doesn't have an order yet.");
                } else {
                    openNewTabView(allOrders);
                }
            } catch(DataQueryException ex){
                showError(ex.getTypeError(), ex.getMessage());
            }
        });

    }

    public void setCustomer(Customer customer) {
        this.selectedCustomer = customer;
    }

    private void openNewTabView(ArrayList<Order> allOrdersFromCustomer) {
        Window displayResult = new Window("FXML/order/index.fxml", "BeerItSimple - All orders from selected customer : " + selectedCustomer.getEntity().getContactName() + " (" + selectedCustomer.getEntity().getPhoneNumber() + ")");
        displayResult.load();
        displayResult.getView().setParentView(this);
        view.order.Index index = (Index) displayResult.getView();
        index.getMainContainer().setStyle("-fx-background-color: linear-gradient(to left, #0f2027, #203a43, #2c5364)");
        index.updateTable(allOrdersFromCustomer);
        index.hideRefreshButton();
        index.setCustomer(this.selectedCustomer);
        displayResult.show();
    }
}
