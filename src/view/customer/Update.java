package view.customer;

import com.jfoenix.controls.*;
import com.sun.source.doctree.ValueTree;
import controller.CityController;
import controller.CustomerController;
import controller.RankController;
import exception.CustomerException;
import exception.NoCustomerFoundException;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.ToggleGroup;
import model.City;
import model.Customer;
import model.Entity;
import model.Rank;
import utils.Validators;
import view.CustomersView;
import view.View;

import java.sql.SQLException;
import java.util.ArrayList;

public class Update extends View {

    @FXML
    JFXRadioButton privateCustomer;
    @FXML
    JFXRadioButton businessCustomer;
    @FXML
    JFXComboBox<Rank> customerRank;
    @FXML
    JFXTextField contactName;
    @FXML
    JFXTextField address;
    @FXML
    JFXTextField houseNumber;
    @FXML
    JFXTextField mail;
    @FXML
    JFXTextField phoneNumber;
    @FXML
    JFXComboBox<City> region;
    @FXML
    JFXTextField accountNumber;
    @FXML
    JFXTextField businessNumber;
    @FXML
    Group businessView;
    @FXML
    JFXButton cancelBtn;
    @FXML
    JFXButton submitBtn;

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

        Validators.setMailValidators(mail);
        Validators.setReqField(contactName);
        Validators.setPhoneNumberValidator(phoneNumber);
        Validators.setAddressValidator(address);
        Validators.setHouseNumberValidator(houseNumber);
        Validators.setAccountNumberValidator(accountNumber);
        Validators.setBusinessNumberValidator(businessNumber);

        contactName.setText(selectedCustomer.getEntity().getContactName());
        address.setText(selectedCustomer.getEntity().getStreet());
        houseNumber.setText(selectedCustomer.getEntity().getHouseNumber().toString());
        mail.setText(selectedCustomer.getEntity().getMail());
        phoneNumber.setText(selectedCustomer.getEntity().getPhoneNumber());
        accountNumber.setText(selectedCustomer.getEntity().getBankAccountNumber());
        businessNumber.setText(selectedCustomer.getEntity().getBusinessNumber());

        if (selectedCustomer.getEntity().getBusinessNumber() != null && selectedCustomer.getEntity().getBankAccountNumber() != null) {
            businessView.setVisible(true);
            businessCustomer.setSelected(true);
        } else {
            businessView.setVisible(false);
            privateCustomer.setSelected(true);
        }

        privateCustomer.setOnAction(e -> {
            businessView.setVisible(false);
        });

        businessCustomer.setOnAction(e -> {
            businessView.setVisible(true);
        });

        ArrayList<City> cityList = cityController.getAllCities();
        ObservableList<City> cityObservableList = FXCollections.observableArrayList(cityList);
        region.setItems(cityObservableList);
        region.getSelectionModel().select(findIndexOfCity(cityObservableList));

        ArrayList<Rank> rankList = rankController.getAllRanks();
        ObservableList<Rank> rankObservableList = FXCollections.observableArrayList(rankList);
        customerRank.setItems(rankObservableList);
        customerRank.getSelectionModel().select(findIndexOfRank(rankObservableList));

        cancelBtn.setOnAction(e -> {
            closeWindow();
        });

        submitBtn.setOnAction(e -> {
                if (Validators.validate(mail, contactName, phoneNumber, address, houseNumber, houseNumber) && checkBusinessCustomer()) {
                    try {
                        if (updateCostumer()) {
                            System.out.println("Done");
                            customersView.updateTable();
                            closeWindow();
                        }


                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
        });
    }

    public void setCustomer(Customer customer) {
        this.selectedCustomer = customer;
    }

    // la fonction indexOF() sur l'observable list ne fonctionne pas
    public int findIndexOfCity(ObservableList<City> cityList) {
        int i = 0;
        for (City city : cityList) {
            if (city.toString().equals(selectedCustomer.getEntity().getCity().toString())) {
                return i;
            }

            i++;
        }

        return -1;
    }

    public int findIndexOfRank(ObservableList<Rank> rankList) {
        int i = 0;
        for (Rank rank : rankList) {
            if (rank.toString().equals(selectedCustomer.getRank().toString())) {
                return i;
            }

            i++;
        }

        return -1;
    }

    private boolean checkBusinessCustomer() {
        if (businessCustomer.isSelected()) {
            return Validators.validate(businessNumber, accountNumber);
        }
        return true;
    }

    public boolean updateCostumer() throws SQLException {
        selectedCustomer.setRank(customerRank.getSelectionModel().getSelectedItem());
        selectedCustomer.getEntity().setContactName(contactName.getText());
        selectedCustomer.getEntity().setStreet(address.getText());
        selectedCustomer.getEntity().setHouseNumber(Integer.parseInt(houseNumber.getText()));
        selectedCustomer.getEntity().setMail(mail.getText());
        selectedCustomer.getEntity().setPhoneNumber(phoneNumber.getText());
        selectedCustomer.getEntity().setCity(region.getValue());
        selectedCustomer.getEntity().setBankAccountNumber(accountNumber.getText());
        selectedCustomer.getEntity().setBusinessNumber(businessNumber.getText());

        return customerController.update(selectedCustomer);
    }
}
