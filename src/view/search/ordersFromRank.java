package view.search;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import controller.OrderController;
import controller.RankController;
import controller.StatusController;
import exception.ConnectionException;
import exception.DataQueryException;
import exception.NullObjectException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import model.Order;
import model.Rank;
import model.Status;
import view.View;
import view.Window;
import view.order.Index;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ordersFromRank extends View implements Initializable {
    @FXML
    private JFXComboBox<Rank> rankList;
    @FXML
    private JFXComboBox<Status> statusList;
    @FXML
    private JFXRadioButton bothRadio;
    @FXML
    private JFXRadioButton paidRadio;
    @FXML
    private JFXRadioButton notPaidRadio;
    @FXML
    private JFXButton searchBtn;

    private RankController rankController;
    private StatusController statusController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }

    @Override
    public void init() {
        try {
            rankController = new RankController();
            statusController = new StatusController();
            rankList.getItems().setAll(rankController.getAllRanks());
            statusList.getItems().addAll(statusController.getAllStatus());
        } catch (ConnectionException | DataQueryException e) {
            showError(e.getTypeError(), e.getMessage());
        }

        rankList.getSelectionModel().selectFirst();
        rankList.getStyleClass().add("whiteComboBox");

        statusList.getItems().add(new Status(null, "No matter"));
        statusList.getSelectionModel().selectFirst();
        statusList.getStyleClass().add("whiteComboBox");

        searchBtn.setOnAction(e -> executeSearch());
    }

    private void executeSearch() {
        Rank rank = rankList.getValue();
        // in the case [no matter] is selected, the id value is null, so we don't search for the status
        Status status = (statusList.getValue().getId() == null ? null : statusList.getValue());

        //  true = paid | false = not paid | Null = Both
        Boolean isPaid = null;

        if (paidRadio.isSelected() || notPaidRadio.isSelected())
            isPaid = paidRadio.isSelected();

        ArrayList<Order> orderFromRanks = null;
        try {
            orderFromRanks = new OrderController().getOrdersFromRanks(rank, status, isPaid);
        } catch (ConnectionException | DataQueryException e) {
            showError(e.getTypeError(), e.getMessage());
        } catch (NullObjectException e) {
            System.out.println(e.getMessage());
        }

        openNewTabview(orderFromRanks);
    }

    private void openNewTabview(ArrayList<Order> orderFromRanks) {
        Window displayResult = new Window("FXML/order/index.fxml", "BeerItSimple - Search result");
        displayResult.load();
        displayResult.getView().setParentView(this);

        Index index = (Index) displayResult.getView();
        index.updateTable(orderFromRanks);
        index.hideRefreshButton();
        index.getMainContainer().setStyle("-fx-background-color: linear-gradient(to left, #0f2027, #203a43, #2c5364)");

        displayResult.show();
    }
}
