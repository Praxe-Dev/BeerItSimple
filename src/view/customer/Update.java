package view.customer;

import com.jfoenix.controls.*;
import controller.CityController;
import controller.CustomerController;
import controller.RankController;
import exception.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import model.City;
import model.Customer;
import model.Rank;
import utils.Validators;
import utils.PopUp;
import view.View;

import java.util.ArrayList;

public class Update extends View {
    @FXML
    private JFXRadioButton privateCustomer;
    @FXML
    private JFXRadioButton businessCustomer;
    @FXML
    private JFXComboBox<Rank> customerRank;
    @FXML
    private JFXTextField contactName;
    @FXML
    private JFXTextField address;
    @FXML
    private JFXTextField houseNumber;
    @FXML
    private JFXTextField mail;
    @FXML
    private JFXTextField phoneNumber;
    @FXML
    private JFXComboBox<City> region;
    @FXML
    private JFXTextField accountNumber;
    @FXML
    private JFXTextField businessNumber;
    @FXML
    private Group businessView;
    @FXML
    private JFXButton cancelBtn;
    @FXML
    private JFXButton submitBtn;

    private Customer selectedCustomer;
    private CityController cityController;
    private RankController rankController;
    private CustomerController customerController;
    private Index customersView;

    @Override
    public void init() {
        try {
            cityController = new CityController();
            rankController = new RankController();
            customerController = new CustomerController();
        } catch (ConnectionException e) {
            showError(e.getTypeError(), e.getMessage());
        }
        customersView = (Index) this.getParentView();

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
        phoneNumber.setText(selectedCustomer.getEntity().getPhoneNumber());

//        Avoid null pointer for the validator
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

        // Display the right format for the customerType
        if (selectedCustomer.getEntity().getBusinessNumber() != null || selectedCustomer.getEntity().getBankAccountNumber() != null) {
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

        ArrayList<City> cityList = null;
        ArrayList<Rank> rankList = null;
        try {
            cityList = cityController.getAllCities();
            rankList = rankController.getAllRanks();
        } catch (DataQueryException e) {
            e.printStackTrace();
        }
        ObservableList<City> cityObservableList = FXCollections.observableArrayList(cityList);
        region.setItems(cityObservableList);
        region.getSelectionModel().select(findIndexOfCity(cityObservableList));

        ObservableList<Rank> rankObservableList = FXCollections.observableArrayList(rankList);
        customerRank.setItems(rankObservableList);
        customerRank.getSelectionModel().select(findIndexOfRank(rankObservableList));

        cancelBtn.setOnAction(e -> {
            closeWindow();
        });

        submitBtn.setOnAction(e -> {
                if (Validators.validate(contactName, phoneNumber, address, houseNumber, houseNumber) && Validators.validateNullableValue(mail, businessNumber, accountNumber)) {
                    try {
                        if (updateCostumer()) {
                            customersView.updateTable();
                            closeWindow();
                        }
                    } catch (DuplicataException | UpdateException exception) {
                        showError(exception.getTypeError(), exception.getMessage());
                    } catch (NullObjectException nullObjectException) {
                        System.out.println(nullObjectException.getMessage());
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

    public boolean updateCostumer() throws DuplicataException, UpdateException, NullObjectException {
        selectedCustomer.setRank(customerRank.getSelectionModel().getSelectedItem());
        selectedCustomer.getEntity().setContactName(contactName.getText());
        selectedCustomer.getEntity().setStreet(address.getText());
        selectedCustomer.getEntity().setHouseNumber(Integer.parseInt(houseNumber.getText()));

        if (mail.getText().equals("")) {
            selectedCustomer.getEntity().setMail(null);
        } else {
            selectedCustomer.getEntity().setMail(mail.getText());
        }

        selectedCustomer.getEntity().setPhoneNumber(phoneNumber.getText());
        selectedCustomer.getEntity().setCity(region.getValue());

        if (accountNumber.getText().equals("")) {
            selectedCustomer.getEntity().setBankAccountNumber(null);
        } else {
            selectedCustomer.getEntity().setBankAccountNumber(accountNumber.getText());
        }

        if (businessNumber.getText().equals("")) {
            selectedCustomer.getEntity().setBusinessNumber(null);
        } else {
            selectedCustomer.getEntity().setBusinessNumber(businessNumber.getText());
        }

        return customerController.update(selectedCustomer);
    }
}
