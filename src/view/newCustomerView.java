package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import controller.CityController;
import controller.CustomerController;
import controller.RankController;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import model.City;
import model.Rank;

/**
 * TODO: Suand on passe de particulier à entreprise ne pas garder les infos entrer précédemment dans Entreprise
  */

public class newCustomerView extends View {

    @FXML
    VBox panel;
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
    JFXComboBox<String> regionBox;
    @FXML
    JFXTextField accountNumber;
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

        // permet lors du clic des radio buttons de changer la vue
        privateCustomer.setOnAction(e -> {
            businessView.setVisible(false);
            accountNumber.clear();
            panel.setPrefHeight(Region.USE_COMPUTED_SIZE);
        });

        businessCustomer.setOnAction(e -> {
            businessView.setVisible(true);
            panel.setPrefHeight(Region.USE_COMPUTED_SIZE);
        });

        // Remplis la combo box
        ObservableList<Rank> rankList = rankController.getAllRanks();
        customerRank.getItems().addAll(rankList);
        customerRank.getSelectionModel().selectFirst();

        ObservableList<String> cityList = cityController.getAllCities();
        regionBox.getItems().addAll(cityList);
        regionBox.getSelectionModel().selectFirst();

        // Associe l'action aux buttons
        cancelBtn.setOnAction(e -> {
            closeWindow();
        });

        submitBtn.setOnAction(e -> {
            insertCustomer();
        });

    }

    private void insertCustomer() {
        // TODO : faire l'insertion
        System.out.println("Pressed submitBtn");

        if (contactName.validate() && phoneNumber.validate()) {
            customerController.newCustomer(/*Passer toutes les infos pour créé le nouveau customer*/);
        } else {
            System.out.println("Ne pas continuer");
        }
    }

    @Override
    public Pane getRoot() {
        return panel;
    }
}
