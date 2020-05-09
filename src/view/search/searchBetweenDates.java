package view.search;

import com.jfoenix.controls.JFXButton;
import controller.OrderController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.BorderPane;
import model.Order;
import view.PopUp;
import view.View;
import view.Window;
import view.order.Index;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class searchBetweenDates extends View implements Initializable {
    @FXML
    DatePicker startingDate;
    @FXML
    DatePicker endDate;
    @FXML
    JFXButton cancelBtn;
    @FXML
    JFXButton searchBtn;

    Index orderIndex;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }

    @Override
    public void init() {
        // to update the table
//        cancelBtn.setOnAction(e -> {
//            // TODO: Find how to close the current pane
//            closeWindow();
//        });

        searchBtn.setOnAction(e -> {
            executeSearch();
        });

//        setShortcut(new KeyCodeCombination(KeyCode.ENTER), () -> executeSearch());
        orderIndex = new Index();
    }

    private boolean validateBothDates(LocalDate start, LocalDate end) {
        return end.isBefore(LocalDate.now().plusDays(1)) && start.isBefore(LocalDate.now().plusDays(1)) ;
    }

    public void executeSearch() {
        LocalDate start = startingDate.getValue() == null ? LocalDate.now() : startingDate.getValue();
//            System.out.println("Start = " + start);
        LocalDate end = endDate.getValue() == null ? LocalDate.now() : endDate.getValue();

        if (validateBothDates(start, end)) {
            ArrayList<Order> orderBetweenDates = new OrderController().getAllOrdersBetweenDates(start, end);

            openNewTabView(orderBetweenDates);
        } else {
            PopUp.showError("Wrong date", "The starting date must be one day earlier than the current date\n (Either the end date won't \"back to the future\")");
        }
    }

    private void openNewTabView(ArrayList<Order> orderBetweenDates) {
        Window displayResult = new Window("FXML/order/index.fxml", "BeerItSimple - Search result");
        displayResult.load();
        displayResult.getView().setParentView(this);
        Index index = (Index) displayResult.getView();

        index.updateTable(orderBetweenDates);
        displayResult.show();
    }
}
