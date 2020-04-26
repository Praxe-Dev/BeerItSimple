package view;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public abstract class View extends Node {

    Window window;
    private View parentView = null;

    public final void init(Window window) {
        this.window = window;
        this.init();
    }

    public abstract void init();

    public abstract Pane getRoot();

    public void switchWindow(Window newWindow) {
        this.window.close();
        newWindow.load();
        newWindow.show();
    }
}
