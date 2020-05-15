package view;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public abstract class View extends Node {

    Window window;
    private View parentView = null;

    public final void init(Window window) {
        this.window = window;
        this.init();
    }

    public abstract void init();

//    public abstract Pane getRoot();

    public void closeWindow() {
        this.window.close();
    }

    public void switchWindow(Window newWindow) {
        this.window.close();
        newWindow.load();
        newWindow.show();
    }

    public void setParentView(View view) {
        this.parentView = view;
    }

    public View getParentView() {
        return this.parentView;
    }

    public void setShortcut(KeyCombination keyCode, Runnable runnable) {
        this.window.getStage().getScene().getAccelerators().put(keyCode, runnable);
    }

    public Stage getStage() {
       return window.getStage();
    }
}
