package view.order;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controller.CustomerController;
import controller.OrderController;
import controller.PaymentMethodController;
import controller.ProductController;
import exception.NoRowSelected;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import model.*;
import utils.Validators;
import view.PopUp;
import view.View;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Create extends View {

    @FXML
    JFXComboBox<Customer> customerList;
    @FXML
    JFXComboBox<PaymentMethod> paymentMethod;
    @FXML
    JFXCheckBox deliveryCheck;
    @FXML
    DatePicker deliveryDate;
    @FXML
    JFXButton cancelBtn;
    @FXML
    JFXButton submitBtn;
    @FXML
    JFXComboBox<Product> productList;
    @FXML
    JFXTextField productQuantity;
    @FXML
    JFXButton addArticleBtn;
    @FXML
    TableView<OrderLineTableFormat> tableArticle;
    @FXML
    TableColumn<OrderLineTableFormat, String> article;
    @FXML
    TableColumn<OrderLineTableFormat, Double> price;
    @FXML
    TableColumn<OrderLineTableFormat, Integer> quantity;
    @FXML
    TableColumn<OrderLineTableFormat, Double> total;
    @FXML
    JFXButton removeArticleBtn;
    @FXML
    Text totalAmount;
    @FXML
    Group deliveryDisplay;



    CustomerController customerController;
    PaymentMethodController paymentMethodController;
    ProductController productController;
    OrderController orderController;

    @Override
    public void init() {
        customerController = new CustomerController();
        paymentMethodController = new PaymentMethodController();
        productController = new ProductController();
        orderController = new OrderController();

        Validators.setNumberValidator(productQuantity);

        ArrayList<Customer> allCustomers = customerController.getAllCustomers();
        customerList.setItems(FXCollections.observableArrayList(allCustomers));
        customerList.getSelectionModel().selectFirst();

        ArrayList<PaymentMethod> allPaymentMethod = paymentMethodController.getAllPaymentMethod();
        paymentMethod.setItems(FXCollections.observableArrayList(allPaymentMethod));
        paymentMethod.getSelectionModel().selectFirst();

        ArrayList<Product> allProducts = productController.getAllProducts();
        productList.setItems(FXCollections.observableArrayList(allProducts));
        productList.getSelectionModel().selectFirst();

        deliveryDisplay.setVisible(false);

       deliveryCheck.setOnAction(e -> {
           if (deliveryDisplay.isVisible()) {
               deliveryDisplay.setVisible(false);
           } else {
               deliveryDisplay.setVisible(true);
           }
       });

       initTable();

       addArticleBtn.setOnAction(e -> {
           if (!productQuantity.getText().equals("") && productQuantity.validate()) {
//               productQuantity.getStyleClass().remove("error");
               productQuantity.setStyle("");
               Product newProduct = productList.getValue();
               if (!checkPresentProduct(newProduct)) {
                    PopUp.showError("Duplicate error", "You try to add a product already present in the command !");
               } else {

                   int productQty = Integer.parseInt(productQuantity.getText());

                   OrderLineTableFormat orderLineTableFormat = new OrderLineTableFormat(newProduct, productQty);
                   tableArticle.getItems().add(orderLineTableFormat);
                   double amount = Double.parseDouble(totalAmount.getText().replace(',', '.'));

                   String newTotal = String.format("%.2f", amount + orderLineTableFormat.getTotal());
                   totalAmount.setText(newTotal);
               }
           } else {
               productQuantity.setStyle("-fx-background-color: rgba(255,0,0,0.5)");
//               productQuantity.getStyleClass().add("error");

           }
       });

       removeArticleBtn.setOnAction(e -> {
           try {

               OrderLineTableFormat orderLineTableFormat = tableArticle.getSelectionModel().getSelectedItem();
               tableArticle.getItems().remove(orderLineTableFormat);
               double amount = Double.parseDouble(totalAmount.getText().replace(',', '.'));

               String newTotal = String.format("%.2f", amount - orderLineTableFormat.getTotal());
               totalAmount.setText(newTotal);
           } catch (NullPointerException exception) {
               new NoRowSelected();
           }
       });

       cancelBtn.setOnAction(e -> {
           closeWindow();
       });

       submitBtn.setOnAction(e -> {
           if (!tableArticle.getItems().isEmpty()) {
                if (deliveryDateCheck()) {

                    try {
                        if (newOrderInsert()) {
                            PopUp.showSuccess("New order added !", "The new order has been added successfully.");
                            Index index = (Index) getParentView();
                            index.updateTable();
                            closeWindow();
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
           }
       });
    }

    private boolean deliveryDateCheck() {
        if (deliveryCheck.isSelected() && LocalDate.now().isAfter(deliveryDate.getValue())) {
            PopUp.showError("Date error", "The delivery date can't be earlier than the current date.");
            return false;
        }
        return true;
    }

    public boolean checkPresentProduct(Product newProduct) {
        ArrayList<OrderLineTableFormat> products = new ArrayList<>(tableArticle.getItems());

        for (OrderLineTableFormat product : products) {
            if (product.getProductCode() == newProduct.getCode())
                return false;
        }

        return true;
    }

    private boolean newOrderInsert() throws SQLException {
        ArrayList<OrderLine> orderLines = new ArrayList<>();
        Product product;
        Delivery delivery = null;

        Order newOrder = new Order(
                customerList.getValue(),
                new GregorianCalendar(),
                paymentMethod.getValue()
        );

        if (deliveryCheck.isSelected()) {
            GregorianCalendar date = new GregorianCalendar();
            date.set(deliveryDate.getValue().getYear(), deliveryDate.getValue().getDayOfMonth(), deliveryDate.getValue().getDayOfMonth());
            delivery = new Delivery(
                    new Employee(2, "admin"),
                    date,
                    newOrder
            );

            newOrder.setDelivery(delivery);
        }

        for (OrderLineTableFormat line : tableArticle.getItems()) {
            product = productList.getItems().get(line.getProductCode() - 1);
            newOrder.addOrderLine(new OrderLine(product, newOrder, line.getQuantity(), line.getUnitPrice()));
        }

        return orderController.create(newOrder);
    }

    private void initTable() {
        article.setCellValueFactory(new PropertyValueFactory<OrderLineTableFormat, String>("product"));
        price.setCellValueFactory(new PropertyValueFactory<OrderLineTableFormat, Double>("unitPrice"));
        quantity.setCellValueFactory(new PropertyValueFactory<OrderLineTableFormat, Integer>("quantity"));
        total.setCellValueFactory(new PropertyValueFactory<OrderLineTableFormat, Double>("total"));
    }
}
