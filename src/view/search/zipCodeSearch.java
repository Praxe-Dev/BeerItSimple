package view.search;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import controller.CityController;

import controller.OrderController;
import exception.ConnectionException;
import exception.DataQueryException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.City;
import model.Order;
import utils.PopUp;
import view.View;
import view.Window;
import view.order.Index;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class zipCodeSearch extends View implements Initializable {
    @FXML
    private JFXComboBox<City> zipCodeBox;
    @FXML
    private JFXButton searchBtn;

    private CityController cityController;
    private OrderController orderController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }

    @Override
    public void init() {
        ArrayList<City> allCities = null;
        try {
            cityController = new CityController();
            orderController = new OrderController();
            allCities = cityController.getAllCities();
        } catch (ConnectionException | DataQueryException e) {
            showError(e.getTypeError(), e.getMessage());
        }

        zipCodeBox.setItems(FXCollections.observableArrayList(allCities));
        zipCodeBox.getSelectionModel().selectFirst();
        zipCodeBox.getStyleClass().add("whiteComboBox");

        searchBtn.setOnAction(e -> {
            search();
        });
    }

    private void search() {
        try {
            City city = zipCodeBox.getSelectionModel().getSelectedItem();
            ArrayList<Order> allOrders = orderController.getAllOrdersFromZipCode(city);
            if(allOrders == null || allOrders.size() == 0){
                PopUp.showError("No order", "There are no orders delivered for this zipcode");
            } else {
                openNewTabView(allOrders, city);
            }
        } catch (DataQueryException e) {
            showError(e.getTypeError(), e.getMessage());
        }
    }

    private void openNewTabView(ArrayList<Order> allOrdersFromZipcode, City city) {
        Window displayResult = new Window("FXML/order/index.fxml", "BeerItSimple - All orders from the selected zipcode : " + city.getZipCode() + " - " + city.getLabel());
        displayResult.load();
        displayResult.getView().setParentView(this);

        Index index = (Index) displayResult.getView();
        index.getMainContainer().setStyle("-fx-background-color: linear-gradient(to left, #0f2027, #203a43, #2c5364)");
        index.updateTable(allOrdersFromZipcode);
        index.hideRefreshButton();
        index.setZipCode(city);

        displayResult.show();
    }

}