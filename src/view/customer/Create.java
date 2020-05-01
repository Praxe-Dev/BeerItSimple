package view.customer;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import controller.CustomerController;
import controller.RankController;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Rank;
import view.View;

import java.io.IOException;
import java.util.ArrayList;

public class Create extends View {
    private CustomerController customerController;
    private RankController rankController;
    private Boolean entrepriseFormShowed = true;
    private double x = 0, y = 0;
    private Pane loadedPane = null;

    @FXML
    private ToggleGroup customerType;
    @FXML
    private JFXRadioButton entrepriseButton;
    @FXML
    private JFXRadioButton particularButton;
    @FXML
    private JFXComboBox rankComboBox;
    @FXML
    private JFXButton addButton;
    @FXML
    private JFXButton cancelButton;
    @FXML
    private VBox createContainer;
    @FXML
    private AnchorPane formPane;

    public Create(){ this.rankController = new RankController(); }

    @Override
    public void init() {
        addFormPane("entreprise"); //Entreprise form on load

        ArrayList<Rank> rankList = rankController.getAllRanks();
        //ObservableList obList = rankController.getAllRanks();
        //rankComboBox.getItems().addAll(obList);
        rankComboBox.getSelectionModel().select(0);

        cancelButton.setOnAction(e -> {
            closeWindow(e);
        });

        addButton.setOnAction(e -> {
            createCustomer(e);
        });

        entrepriseButton.setUserData("entreprise");
        entrepriseButton.selectedProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov) {
                if (entrepriseButton.isSelected()) {
                    if(entrepriseFormShowed) {
                        System.out.print("Click on entreprise but already showed");
                    } else {
                        //Afficher formulaire type entreprise
                        System.out.print("Click on entreprise");
                        addFormPane("entreprise");
                        entrepriseFormShowed = true;
                    }
                }
            }
        });

        particularButton.setUserData("particular");
        particularButton.selectedProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov) {
                if (particularButton.isSelected()) {
                    if(!entrepriseFormShowed) {
                        System.out.print("Click on particular but already showed");
                    } else {
                        //Afficher formulaire type particulier
                        System.out.print("Click on particular");
                        addFormPane("particular");
                        entrepriseFormShowed = false;
                    }
                }
            }
        });

        makeDraggable();
    }

    private void addFormPane(String type){
        if(type != null) {
            try {
                if(type.equals("entreprise")){
                    loadedPane = FXMLLoader.load(getClass().getResource("/FXML/customer/forms/entreprise.fxml"));
                } else if(type.equals("particular")){
                    loadedPane = FXMLLoader.load(getClass().getResource("/FXML/customer/forms/particular.fxml"));

                }
                formPane.getChildren().clear();
                formPane.getChildren().add(loadedPane);
            } catch (IOException e) {
                System.out.print(e.getMessage());
            }
        }
    }

    private void createCustomer(ActionEvent e) {
        //Req field
        RequiredFieldValidator reqField = new RequiredFieldValidator();
        reqField.setMessage("Required field");

        //Number field
        NumberValidator numbValid = new NumberValidator();
        numbValid.setMessage("Only number allowed");

        JFXTextField contactName = getContactName();
        contactName.getValidators().add(reqField);
        contactName.validate();

        JFXTextField houseNumber = getHouseNumber();
        houseNumber.getValidators().add(reqField);
        houseNumber.validate();
        houseNumber.getValidators().add(numbValid);
        houseNumber.validate();



        String clientType = customerType.getSelectedToggle().getUserData().toString();
        //try{
            if(clientType.equals("entreprise")) {
                System.out.println("ENTREPRISE CONTACT NAME = " + ((JFXTextField) loadedPane.lookup("#contactName")).getText());

            } else if(clientType.equals("particular")){
                System.out.println("PARTICULAR CONTACT NAME = " + ((JFXTextField) loadedPane.lookup("#contactName")).getText());

            }
            //Try Create
            //(Integer id, String mail, String contactName, String phoneNumber, Integer houseNumber, String street, String fax, String bankAccountNumber, String VATNumber, String cityLabel, Integer cityZipCode, Integer rankId
            //Customer customer = new Customer(13, );

        /*} catch(CustomerException exception){
            exception.showMessage();
        }*/

    }

    //TODO: Deplacer les champs communs dans le fxml create

    public JFXTextField getContactName(){
        JFXTextField contactName = ((JFXTextField) loadedPane.lookup("#contactName"));
        if(contactName.getText().isBlank()){
            //edit
            return contactName;
        } else {
            return contactName;
        }
    }

    public JFXTextField getHouseNumber(){
        return ((JFXTextField) loadedPane.lookup("#houseNumber"));
    }

    private void closeWindow(ActionEvent e){
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.close();
    }

    private void makeDraggable(){
        createContainer.setOnMousePressed(e -> {
           x = e.getSceneX();
           y = e.getSceneY();
        });

        createContainer.setOnMouseDragged(e -> {
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setX(e.getScreenX() - x);
            stage.setY(e.getScreenY() - y);
        });
    }

    public Pane getRoot() {
        return this.createContainer;
    }
}
