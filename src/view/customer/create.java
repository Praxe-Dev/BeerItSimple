package view.customer;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.IntegerValidator;
import com.jfoenix.validation.RegexValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import controller.CityController;
import controller.CustomerController;
import controller.RankController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.City;
import model.Customer;
import model.Entity;
import model.Rank;
import view.PopUp;
import view.View;

import java.sql.SQLException;
import java.util.ArrayList;

public class create extends View {

    @FXML
    VBox panel;
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

        // Initialise l'objet reqField qui, ajouté aux textField, permet de vérifier que ceux ci sont initalisés
        RequiredFieldValidator reqField = new RequiredFieldValidator();
        reqField.setMessage("Required field");
        RegexValidator mailValidator = new RegexValidator();
        mailValidator.setRegexPattern("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
        mailValidator.setMessage("The mail format is not valid");

        mail.getValidators().add(mailValidator);
        mail.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!mail.getText().equals("")) {
                System.out.println(newVal);
                System.out.println(oldVal);
                System.out.println(mail.getText());
                mail.validate();
            }
        });

        contactName.getValidators().add(reqField);
        contactName.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                contactName.validate();
            }
        });

        RegexValidator phoneNumberValidator = new RegexValidator();
        phoneNumberValidator.setRegexPattern("^(\\d{4})\\/(\\d{2})\\.(\\d{2})\\.(\\d{2})$");
        phoneNumberValidator.setMessage("Phone number format : xxxx/xx.xx.xx");
        phoneNumber.getValidators().addAll(reqField, phoneNumberValidator);
        phoneNumber.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                phoneNumber.validate();
            }
        });

        address.getValidators().add(reqField);
        address.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                address.validate();
            }
        });

        IntegerValidator houseNumberValidator = new IntegerValidator();
        houseNumberValidator.setMessage("Should be a number");
        houseNumber.getValidators().add(reqField);
        houseNumber.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                houseNumber.validate();
            }
        });

        RegexValidator accountNumberValidator = new RegexValidator();
        accountNumberValidator.setRegexPattern("^([A-Z]{2})(\\d{14})$");
        accountNumberValidator.setMessage("Format : BEXXXXXXXXXXXXXX");
        accountNumber.getValidators().addAll(reqField, accountNumberValidator);
        accountNumber.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                accountNumber.validate();
            }
        });

        RegexValidator businessNumberValidator = new RegexValidator();
        businessNumberValidator.setRegexPattern("^(\\d{4})\\.(\\d{3})\\.(\\d{3})$");
        businessNumberValidator.setMessage("Format : XXXX.XXX.XXX");
        businessNumber.getValidators().addAll(businessNumberValidator);
        businessNumber.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                businessNumber.validate();
            }
        });

//        submitBtn.disableProperty().bind((
//                contactName.textProperty().isNotEmpty()).not());

        // permet lors du clic des radio buttons de changer la vue
        privateCustomer.setUserData("private");
        privateCustomer.setOnAction(e -> {
            businessView.setVisible(false);
        });

        businessCustomer.setUserData("business");
        businessCustomer.setOnAction(e -> {
            businessView.setVisible(true);
        });

        //Rempli la combobox Rank
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
                if(validateData()) {
                    System.out.println("INSERTION");
                    try {
                        if (insertCustomer())
                            PopUp.showSuccess("Client créé avec succès !", "Success");
                            closeWindow();
                    } catch (SQLException exception) {
                        PopUp.showError(exception.getMessage(), "Error");
                    }
                }
        });

    }

    private boolean validateData() {
        return contactName.validate() && phoneNumber.validate() && address.validate() && houseNumber.validate() && checkBusinessCustomer();
    }

    /**
     * @return True if particular cause you don't need those informations for a particular customer
     */
    private boolean checkBusinessCustomer() {
        if (businessCustomer.isSelected()) {
            return accountNumber.validate() && businessNumber.validate();
        }
        return true;
    }

    /**
     * VATNumber supprimé car calculable. "BE" + BusinessNumber
     *
     */

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
                newEntity.setVATNumber(businessNumber.getText());
                newEntity.setBusinessNumber(businessNumber.getText());
            }
            Rank selectedRank = customerRank.getSelectionModel().getSelectedItem();
            City city = regionBox.getSelectionModel().getSelectedItem();
            newEntity.setCity(city);
            newCustomer = new Customer(newEntity, selectedRank);
            return customerController.create(newCustomer);
        } catch (SQLException err) {
            throw err;
        }
    }

    @Override
    public Pane getRoot() {
        return panel;
    }
}
