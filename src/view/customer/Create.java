package view.customer;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import controller.CustomerController;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.View;
import java.io.IOException;

public class Create extends View {
    private CustomerController customerController;
    private Boolean entrepriseFormShowed = true;
    private double x = 0, y = 0;

    @FXML
    private JFXRadioButton entrepriseButton;
    @FXML
    private JFXRadioButton particularButton;
    @FXML
    private JFXButton addButton;
    @FXML
    private JFXButton cancelButton;
    @FXML
    private VBox createContainer;
    @FXML
    private AnchorPane formPane;

    @Override
    public void init() {
        addFormPane("entreprise"); //Entreprise form on load

        cancelButton.setOnAction(e -> {
            closeWindow(e);
        });

        addButton.setOnAction(e -> {
            createCustomer(e);
        });

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
                Pane newLoadedPane = null;
                if(type.equals("entreprise")){
                    newLoadedPane = FXMLLoader.load(getClass().getResource("/FXML/customer/forms/entreprise.fxml"));
                } else if(type.equals("particular")){
                    newLoadedPane = FXMLLoader.load(getClass().getResource("/FXML/customer/forms/particular.fxml"));
                }
                formPane.getChildren().clear();
                formPane.getChildren().add(newLoadedPane);
            } catch (IOException e) {
                System.out.print(e.getMessage());
            }
        }
    }

    private void createCustomer(ActionEvent e){
        System.out.print("Creation");
        /*
        try{
            //Try Create
        } catch(CustomerException exception){
            exception.showMessage();
        }
         */
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
