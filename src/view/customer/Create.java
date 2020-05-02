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

import java.sql.SQLException;
import java.util.ArrayList;

public class Create extends View {

    @FXML
    ToggleGroup customerType;
    @FXML
    JFXRadioButton privateCustomer;
    @FXML
    JFXRadioButton businessCustomer;
    @FXML
    JFXComboBox<Rank> customerRank;
    @FXML
    JFXTextField contactName;
    @FXML
    JFXTextField phoneNumber;
    @FXML
    JFXTextField mail;
    @FXML
    JFXTextField houseNumber;
    @FXML
    JFXTextField address;
    @FXML
    JFXComboBox<City> regionBox;
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

    RankController rankController;
    CityController cityController;
    CustomerController customerController;

    @Override
    public void init() {
        // Initialise le controller
        rankController = new RankController();
        cityController = new CityController();
        customerController = new CustomerController();

        // affiche de base pour un client particulier
        businessView.setVisible(false);

        // Initialise l'objet reqField qui, ajouter aux textField, permet de vérifier que ceux ci sont initaliser
//        RequiredFieldValidator reqField = new RequiredFieldValidator();
//        reqField.setMessage("Required field");

        Validators.setMailValidators(mail);
        Validators.setReqField(contactName);
        Validators.setPhoneNumberValidator(phoneNumber);
        Validators.setAddressValidator(address);
        Validators.setHouseNumberValidator(houseNumber);
        Validators.setAccountNumberValidator(accountNumber);
        Validators.setBusinessNumberValidator(businessNumber);

        // permet lors du clic des radio buttons de changer la vue
//        privateCustomer.setUserData("private");
        privateCustomer.setOnAction(e -> {
            businessView.setVisible(false);
        });

//        businessCustomer.setUserData("business");
        businessCustomer.setOnAction(e -> {
            businessView.setVisible(true);
        });

        //Remplis la combobox Rank
        ArrayList<Rank> rankList = rankController.getAllRanks();
        customerRank.setItems(FXCollections.observableArrayList(rankList));
        customerRank.getSelectionModel().selectFirst();
//        customerRank.setConverter(new StringConverter<Rank>() {
//            @Override
//            public String toString(Rank object) {
//                return object.getLabel() + " (" + object.getCreditLimit() + "€)";
//            }
//
//            @Override
//            public Rank fromString(String string) {
//                return null;
//            }
//        });

        /*ObservableList<String> cityList = cityController.getAllCities();
        regionBox.getItems().addAll(cityList);
        */
        ArrayList<City> cityList = cityController.getAllCities();
        regionBox.setItems(FXCollections.observableArrayList(cityList));
        regionBox.getSelectionModel().selectFirst();
//        regionBox.setConverter(new StringConverter<City>() {
//            @Override
//            public String toString(City object) {
//                return object.getZipCode() + " " + object.getLabel();
//            }
//
//            @Override
//            public City fromString(String string) {
//                return null;
//            }
//        });


        // Associe l'action aux buttons
        cancelBtn.setOnAction(e -> {
            closeWindow();
        });

        submitBtn.setOnAction(e -> {
//            try {
//                int status = 0;
            if(Validators.validate(contactName, phoneNumber, address, houseNumber) && checkBusinessCustomer()) {
                System.out.println("INSERTION");
                try {
                    insertCustomer();
                    PopUp.showSuccess("Client créé avec succès !", "Success");
                    CustomersView customersView = (CustomersView) getParentView();
                    customersView.updateTable();
                    closeWindow();
                } catch (SQLException exception) {
                    PopUp.showError(exception.getMessage(), "Error");
                }
            }
        });

    }


    /**
     * @return True if particular cause you don't need those informations for a particular customer
     */
    private boolean checkBusinessCustomer() {
        if (businessCustomer.isSelected()) {
            return Validators.validate(businessNumber, accountNumber);
        }
        return true;
    }

    private boolean insertCustomer() throws SQLException{
        try {
            Customer newCustomer;
            Entity newEntity = new Entity();

            newEntity.setContactName(contactName.getText());
            newEntity.setPhoneNumber(phoneNumber.getText());
            newEntity.setMail(mail.getText());
            newEntity.setStreet(address.getText());
            newEntity.setHouseNumber(Integer.parseInt(houseNumber.getText()));

            if(businessCustomer.isSelected()) {
                newEntity.setBankAccountNumber(accountNumber.getText());
                newEntity.setBusinessNumber(businessNumber.getText());
            }
            Rank selectedRank = customerRank.getValue();
            City city = regionBox.getValue();
            newEntity.setCity(city);
            newCustomer = new Customer(newEntity, selectedRank);
            return customerController.create(newCustomer);
        } catch (SQLException err) {
            throw err;
        }
    }
}
