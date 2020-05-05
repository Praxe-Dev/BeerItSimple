package view.order;

import com.jfoenix.controls.JFXButton;
import controller.OrderController;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import model.Order;
import view.PopUp;
import view.View;

import java.time.LocalDate;
import java.util.ArrayList;

public class Search extends View {

    @FXML
    Tab dateSearchpane;
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
    public void init() {
        // to update the table
        orderIndex = (Index) getParentView();

        cancelBtn.setOnAction(e -> {
            closeWindow();
        });

        searchBtn.setOnAction(e -> {
            executeSearch();
        });

        setShortcut(new KeyCodeCombination(KeyCode.ENTER), () -> executeSearch());
    }

    private boolean validateBothDates(LocalDate start, LocalDate end) {
        return end.isBefore(LocalDate.now().plusDays(1)) && start.isBefore(end) ;
    }

    public void executeSearch() {
        LocalDate start = startingDate.getValue() == null ? LocalDate.now() : startingDate.getValue();
//            System.out.println("Start = " + start);
        LocalDate end = endDate.getValue() == null ? LocalDate.now() : endDate.getValue();

        if (validateBothDates(start, end)) {
            ArrayList<Order> orderBetweenDates = new OrderController().getAllOrdersBetweenDates(start, end.plusDays(1));
            if (orderIndex.updateTable(orderBetweenDates)) {
                closeWindow();
            }
        } else {
            PopUp.showError("Wrong date", "The starting date must be one day earlier than the current date\n (Either the end date won't \"back to the future\")");
        }
    }
}
