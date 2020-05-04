package view.customer;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import controller.CityController;
import controller.CustomerController;
import controller.RankController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.ToggleGroup;
import model.City;
import model.Customer;
import model.Entity;
import model.Rank;
import utils.Validators;
import view.CustomersView;
import view.PopUp;
import view.View;


public class Read extends View {

    @FXML
    JFXRadioButton privateCustomer;
    @FXML
    JFXRadioButton businessCustomer;
    @FXML
    JFXTextField customerRank;
    @FXML
    JFXTextField contactName;
    @FXML
    JFXTextField phoneNumber;
    @FXML
    JFXTextField mail;
    @FXML
    JFXTextField houseNumber;
    @FXML
    JFXTextField region;
    @FXML
    JFXTextField address;
    @FXML
    JFXTextField accountNumber;
    @FXML
    JFXTextField businessNumber;
    @FXML
    JFXButton cancelBtn;
    @FXML
    JFXButton viewOrdersBtn;

    Customer selectedCustomer;
    CityController cityController;
    RankController rankController;
    CustomerController customerController;
    CustomersView customersView;

    @Override
    public void init() {
        cityController = new CityController();
        rankController = new RankController();
        customerController = new CustomerController();
        customersView = (CustomersView) this.getParentView();

        cancelBtn.setOnAction(e -> {
            closeWindow();
        });
    }

    public void setCustomer(Customer customer) {
        this.selectedCustomer = customer;
    }
}
