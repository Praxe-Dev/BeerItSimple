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
import view.PopUp;
import view.View;

import java.sql.SQLException;
import java.util.ArrayList;

public class Create extends View {
    @FXML
    private ToggleGroup customerType;
    @FXML
    private JFXRadioButton privateCustomer;
    @FXML
    private JFXRadioButton businessCustomer;
    @FXML
    private JFXComboBox<Rank> customerRank;
    @FXML
    private JFXTextField contactName;
    @FXML
    private JFXTextField phoneNumber;
    @FXML
    private JFXTextField mail;
    @FXML
    private JFXTextField houseNumber;
    @FXML
    private JFXTextField address;
    @FXML
    private JFXComboBox<City> regionBox;
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

        Validators.setMailValidators(mail);
        Validators.setNoNumberValidator(contactName);
        Validators.setPhoneNumberValidator(phoneNumber);
        Validators.setNoNumberValidator(address);
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

        ArrayList<City> cityList = cityController.getAllCities();
        regionBox.setItems(FXCollections.observableArrayList(cityList));
        regionBox.getSelectionModel().selectFirst();


        // Associe l'action aux buttons
        cancelBtn.setOnAction(e -> {
            closeWindow();
        });

        submitBtn.setOnAction(e -> {
            if(Validators.validate(contactName, phoneNumber, address, houseNumber)  && Validators.validateNullableValue(mail, businessNumber, accountNumber)) {
                try {
                    insertCustomer();
                    PopUp.showSuccess("Client créé avec succès !", "Success");
                    Index customersView = (Index) getParentView();
                    customersView.updateTable();
                    closeWindow();
                } catch (SQLException exception) {
                    PopUp.showError(exception.getMessage(), "Error");
                }
            }
        });
    }

    private boolean insertCustomer() throws SQLException{

        Customer newCustomer;
        Entity newEntity = new Entity();

        newEntity.setContactName(contactName.getText());
        newEntity.setPhoneNumber(phoneNumber.getText());
        newEntity.setMail(mail.getText());
        newEntity.setStreet(address.getText());
        newEntity.setHouseNumber(Integer.parseInt(houseNumber.getText()));

        if(businessCustomer.isSelected()) {
            if (accountNumber.validate())
                newEntity.setBankAccountNumber(accountNumber.getText());

            if (businessNumber.validate())
                newEntity.setBusinessNumber(businessNumber.getText());
        }

        Rank selectedRank = customerRank.getValue();
        City city = regionBox.getValue();
        newEntity.setCity(city);
        newCustomer = new Customer(newEntity, selectedRank);
        return customerController.create(newCustomer);
    }
}
