package view.search;

import com.jfoenix.controls.JFXButton;
import controller.OrderController;
import exception.ConnectionException;
import exception.DataQueryException;
import exception.NullObjectException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import model.Order;
import utils.PopUp;
import view.View;
import view.Window;
import view.order.Index;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class searchBetweenDates extends View implements Initializable {
    @FXML
    private DatePicker startingDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private JFXButton searchBtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }

    @Override
    public void init() {

        startingDate.setValue(LocalDate.now().minusMonths(1));
        endDate.setValue(LocalDate.now());

        searchBtn.setOnAction(e -> {
            executeSearch();
        });
    }

    public void executeSearch() {
        LocalDate start = startingDate.getValue();
        LocalDate end = null;

        if (endDate.getValue() != null)
             end = endDate.getValue().plusDays(1);

        if (validateBothDates(start, end)) {
            ArrayList<Order> orderBetweenDates = null;
            try {
                orderBetweenDates = new OrderController().getAllOrdersBetweenDates(start, end);
            } catch (ConnectionException | DataQueryException e) {
                showError(e.getTypeError(), e.getMessage());
            } catch (NullObjectException e) {
                System.out.println(e.getMessage());
            }
            openNewTableView(orderBetweenDates);
        } else {
            PopUp.showError("Wrong date", "The starting date must be one day earlier than the current date\n (Either the end date won't \"back to the future\")");
        }
    }

    private void openNewTableView(ArrayList<Order> orderBetweenDates) {
        Window displayResult = new Window("FXML/order/index.fxml", "BeerItSimple - Search result");
        displayResult.load();
        displayResult.getView().setParentView(this);

        Index index = (Index) displayResult.getView();
        index.updateTable(orderBetweenDates);
        index.hideRefreshButton();
        index.getMainContainer().setStyle("-fx-background-color: linear-gradient(to left, #0f2027, #203a43, #2c5364)");

        displayResult.show();
    }

    private boolean validateBothDates(LocalDate start, LocalDate end) {
            return start != null && end != null && end.isBefore(LocalDate.now().plusDays(2)) && start.isBefore(end) ;
    }
}
