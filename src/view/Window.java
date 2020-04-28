package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Window extends Parent {

    private final String pathToFxml;
    private String title;
    private final Stage stage;
    private Parent parent;
    private View view;

    /**
     * Constructeur de la fenêtre afficher
     * @param pathToFxml est le chemin vers le fichier FXML permettant l'affichage
     * @param title est le titre de la fenêtre
     */
    public Window (String pathToFxml, String title) {

        this.pathToFxml = pathToFxml;
        this.title = title;
        this.stage = new Stage();
//        this.stage.getIcons().add(new Image("/ressources/logoTest.PNG"));
    }

    public void load() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            parent = fxmlLoader.load(getClass().getClassLoader().getResource(pathToFxml).openStream());
            view = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void show() {
        stage.setTitle(title);
        stage.setScene(new Scene(parent));
        view.init(this);
        stage.show();
    }

//    public Parent getParent() {
//        return this.parent;
//    }

    public void resizable(Boolean isResizable){
        stage.setResizable(isResizable);
    }

    public void undecorated(){
        stage.initStyle(StageStyle.UNDECORATED);
    }

    public void close() {
        stage.close();
    }

    public Stage getStage() {
        return this.stage;
    }
}
