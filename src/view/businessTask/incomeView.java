package view.businessTask;

import com.jfoenix.controls.JFXButton;
import controller.ProductController;
import exception.DateException;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import model.OrderTableFormat;
import model.Product;
import model.ProductIncome;
import utils.Validators;
import view.PopUp;
import view.View;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Function;

public class incomeView extends View {

    @FXML
    DatePicker startDate;
    @FXML
    DatePicker endDate;
    @FXML
    JFXButton refreshBtn;
    @FXML
    TableView<ProductIncome> incomeTable;
    @FXML
    TableColumn<ProductIncome, Integer> productCode;
    @FXML
    TableColumn<ProductIncome, String> productName;
    @FXML
    TableColumn<ProductIncome, Integer> amountSold;
    @FXML
    TableColumn<ProductIncome, Double> income;
    @FXML
    TableColumn<ProductIncome, Double> salePercentage;
    @FXML
    Text totalIncome;


    @Override
    public void init() {
        refreshBtn.setOnAction(e -> {
            LocalDate start = startDate.getValue();
            LocalDate end = endDate.getValue();

            if (Validators.validateBetweenDates(start, end)) {
                ArrayList<ProductIncome> productIncomes = new ProductController().getAllProductsIncome(start, end);
                updateTable(productIncomes);

            } else {
                new DateException();
            }
        });

        startDate.setValue(LocalDate.now().minusYears(1));
        endDate.setValue(LocalDate.now());

        ArrayList<ProductIncome> productIncomes = new ProductController().getAllProductsIncome(startDate.getValue(), endDate.getValue());
        double total = 0;
        for (ProductIncome p : productIncomes) {
            total += p.getSalePercentageNumber();
        }

        initTable();
        updateTable(productIncomes);
    }

    void initTable() {
        productCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        productName.setCellValueFactory(new PropertyValueFactory<>("label"));
        amountSold.setCellValueFactory(new PropertyValueFactory<>("amountSold"));
        income.setCellValueFactory(new PropertyValueFactory<>("income"));
        salePercentage.setCellValueFactory(new PropertyValueFactory<>("salePercentage"));

        incomeTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        for (int i = 0; i < incomeTable.getColumns().size(); i++) {
            incomeTable.getColumns().get(i).prefWidthProperty().bind(incomeTable.widthProperty().multiply((double) 1 / incomeTable.getColumns().size()));
        }
    }

    void updateTable(ArrayList<ProductIncome> products) {
        incomeTable.getItems().setAll(products);

        double total = products.stream()
                                .mapToDouble(ProductIncome::getIncome)
                                .sum();

        totalIncome.setText(String.format("%.2f", total));
    }
}
