package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import controller.CityController;
import controller.CustomerController;
import controller.RankController;
import exception.CustomerException;
import exception.SQLManageException;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.StringConverter;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import model.City;
import model.Customer;
import model.Entity;
import model.Rank;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class newCustomerView extends View {

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
    JFXTextField fax;
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
    JFXTextField VATNumber;
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

        // affiche pour un client particulier
        businessView.setVisible(false);

        // Initialise l'objet reqField qui, ajouter aux textField, permet de vérifier que ceux ci sont initaliser
        RequiredFieldValidator reqField = new RequiredFieldValidator();
        reqField.setMessage("Required field");

        // Ajoute les champs requis
        contactName.getValidators().add(reqField);
        phoneNumber.getValidators().add(reqField);
        address.getValidators().add(reqField);
        houseNumber.getValidators().add(reqField);
        accountNumber.getValidators().add(reqField);
        businessNumber.getValidators().add(reqField);
        VATNumber.getValidators().add(reqField);

        // permet lors du clic des radio buttons de changer la vue
        privateCustomer.setUserData("private");
        privateCustomer.setOnAction(e -> {
            businessView.setVisible(false);
            //Le clear n'est pas nécessaire car on passe dans le validator uniquement si la bonne case est cochée. Champs remplis ou non.
            //accountNumber.clear();
            //businessNumber.clear();
            //VATNumber.clear();
            panel.setPrefHeight(Region.USE_COMPUTED_SIZE);
        });

        businessCustomer.setUserData("business");
        businessCustomer.setOnAction(e -> {
            businessView.setVisible(true);
            panel.setPrefHeight(Region.USE_COMPUTED_SIZE);
        });

        //Remplis la combobox Rank
        ArrayList<Rank> rankList = rankController.getAllRanks();
        customerRank.setItems(FXCollections.observableArrayList(rankList));
        customerRank.getSelectionModel().selectFirst();
        customerRank.setConverter(new StringConverter<Rank>() {
            @Override
            public String toString(Rank object) {
                return object.getLabel() + " (" + object.getCreditLimit() + "€)";
            }

            @Override
            public Rank fromString(String string) {
                return null;
            }
        });

        /*ObservableList<String> cityList = cityController.getAllCities();
        regionBox.getItems().addAll(cityList);
        */
        ArrayList<City> cityList = cityController.getAllCities();
        regionBox.setItems(FXCollections.observableArrayList(cityList));
        regionBox.getSelectionModel().selectFirst();
        regionBox.setConverter(new StringConverter<City>() {
            @Override
            public String toString(City object) {
                return object.getZipCode() + " " + object.getLabel();
            }

            @Override
            public City fromString(String string) {
                return null;
            }
        });

        // Associe l'action aux buttons
        cancelBtn.setOnAction(e -> {
            closeWindow();
        });

        submitBtn.setOnAction(e -> {
            try {
                int status = 0;
                status = validateData();
                //System.out.println(status);
                if(status != 0){
                    closeWindow();
                    new CustomerException("Client créé avec succès !", "Success").showMessage();
                }
            } catch (CustomerException err){
                err.showMessage();
            } catch(SQLException ex){
                new SQLManageException(ex).showMessage();
            }
        });

    }

    private int validateData() throws CustomerException, SQLException {
        String custType = customerType.getSelectedToggle().getUserData().toString();
        if(!contactName.validate() || !phoneNumber.validate() || !houseNumber.validate() || !address.validate()){
            throw new CustomerException("Please complete all fields");
        } else {
            //Tout est rempli, on vérifie les types de données entrés
            System.out.println(mail.getText());
            if(mail.getText() != null){
                String regex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
                if(!mail.getText().matches(regex)) throw new CustomerException("Please verify email format");
            }

            String phoneRegex = "^(\\d{4})\\/(\\d{2})\\.(\\d{2})\\.(\\d{2})$";
            if(!phoneNumber.getText().matches(phoneRegex)) throw new CustomerException("Please verify phone number format. Required format: 0123/45.67.89");
        }

        if(custType.equals("business")) {
            //Business validation
            if(!VATNumber.validate() || !businessNumber.validate() || !accountNumber.validate()){
                throw new CustomerException("Please complete all fields");
            } else {
                //Tout est rempli, on vérifie les types de données entrés
                String accountRegex = "^([A-Z]{2})(\\d{14})$";
                if(!accountNumber.getText().matches(accountRegex)) throw new CustomerException("Please verify account number format. Required format: IBAN");

                String businessNumberRegex = "^(\\d{4})\\.(\\d{3})\\.(\\d{3})$";
                if(!businessNumber.getText().matches(businessNumberRegex)) throw new CustomerException("Please verify business number format. Required format: 0123.456.789");

                if(fax.getText() != null){
                    String faxRegex = "^(\\d{3})\\/(\\d{2})\\.(\\d{2})\\.(\\d{2})$";
                    if(!fax.getText().matches(faxRegex)) throw new CustomerException("Please verify fax format. Required format: 012/22.22.22");
                }
            }
        }
        return insertCustomer();
    }

    /**
     * Supprimer le champ VAT car juste le business number avec BE devant donc calculable ?
     * Passer le type de houseNumber à varchar/string au lieu de int ? Quelle règle regex ?
     *
     */

    private int insertCustomer() throws SQLException{
        System.out.println("Insert customer");
        int status = 0;
        try {
            Customer newCustomer;
            String custType = customerType.getSelectedToggle().getUserData().toString();
            Entity newEntity = new Entity();
            newEntity.setContactName(contactName.getText());
            newEntity.setPhoneNumber(phoneNumber.getText());
            newEntity.setMail(mail.getText());
            newEntity.setStreet(address.getText());
            newEntity.setHouseNumber(Integer.parseInt(houseNumber.getText()));

            if(custType.equals("business")) {
                newEntity.setBankAccountNumber(accountNumber.getText());
                newEntity.setVATNumber("BE" + businessNumber.getText());
                newEntity.setFax(fax.getText());
                newEntity.setBusinessNumber(businessNumber.getText());
            }
            Rank selectedRank = customerRank.getSelectionModel().getSelectedItem();
            City city = regionBox.getSelectionModel().getSelectedItem();
            newEntity.setCity(city);
            newCustomer = new Customer(newEntity, selectedRank);
            status = customerController.create(newCustomer);
        } catch (SQLException err) {
            throw err;
        }
        return status;
    }

    @Override
    public Pane getRoot() {
        return panel;
    }
}
