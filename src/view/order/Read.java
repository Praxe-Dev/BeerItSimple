package view.order;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import controller.OrderController;
import exception.NoRowSelected;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import model.*;
import view.View;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Read extends View {

    @FXML
    Label orderReference;
    @FXML
    JFXTextField customer;
    @FXML
    JFXTextField paymentMethod;
    @FXML
    JFXCheckBox deliveryCheck;
    @FXML
    Group deliveryDisplay;
    @FXML
    JFXTextField deliveryDate;
    @FXML
    JFXButton okBtn;
    @FXML
    TableView<OrderLineTableFormat> tableArticle;
    @FXML
    TableColumn<OrderLineTableFormat, String> article;
    @FXML
    TableColumn<OrderLineTableFormat, Double> price;
    @FXML
    TableColumn<OrderLineTableFormat, Integer> quantity;
    @FXML
    TableColumn<OrderLineTableFormat, Double> totalExclVat;
    @FXML
    TableColumn<OrderLineTableFormat, Double> totalInclVat;
    @FXML
    Text totalAmountExclVat;
    @FXML
    Text totalAmountVatOnly;
    @FXML
    Text totalAmountVatInc;

//    OrderController orderController;

    Order selectedOrder;

    @Override
    public void init() {
        okBtn.setOnAction(e -> closeWindow());
//        orderController = new OrderController();
//        Order order = orderController.getOrder();
        orderReference.setText("[" + selectedOrder.getReference() + "]");

        if (selectedOrder.getCustomer() != null)
            customer.setText(selectedOrder.getCustomer().getEntity().getContactName());
        else
            customer.setText("N/A");

        paymentMethod.setText(selectedOrder.getPaymentMethod().getLabel());

        deliveryCheck.setSelected(selectedOrder.getDelivery() != null);

        deliveryDisplay.setVisible(deliveryCheck.isSelected());
        if (deliveryDisplay.isVisible()) {
            SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
            String formatedDate = fmt.format(selectedOrder.getDelivery().getPlannedDate().getTime());
            deliveryDate.setText(formatedDate);
        }

        initTable();

        double amountExclVat = 0;
        double amountOnlyVat = 0;
        for (OrderLineTableFormat ol : tableArticle.getItems()) {
            amountExclVat += ol.getExclVat();
            amountOnlyVat += ol.getExclVat() * (ol.getVatCodeRate() / 100.0);
        }

        double totalAmount = amountExclVat + amountOnlyVat;
        totalAmountExclVat.setText(String.format("%.2f", amountExclVat));
        totalAmountVatOnly.setText(String.format("%.2f", amountOnlyVat));
        totalAmountVatInc.setText(String.format("%.2f", totalAmount));
    }

    private void initTable() {
//        customerId.setCellValueFactory(new PropertyValueFactory<CustomerTableFormat, Integer>("id"));
        try {

            article.setCellValueFactory(new PropertyValueFactory<>("product"));
            price.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
            quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            totalExclVat.setCellValueFactory(new PropertyValueFactory<>("exclVat"));
            totalInclVat.setCellValueFactory(new PropertyValueFactory<>("inclVat"));

            ArrayList<OrderLineTableFormat> orderLines = new ArrayList<>();

            for (OrderLine ol : new OrderController().getOrder(selectedOrder.getReference()).getOrderLineList()) {
                orderLines.add(new OrderLineTableFormat(ol.getProduct(), ol.getQuantity()));
            }

            tableArticle.getItems().addAll(orderLines);
        } catch (NoRowSelected e) {
            e.showError();
        }
    }

    public void setOrder(Order order) {
        this.selectedOrder = order;
    }
}
