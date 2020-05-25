package view.search;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import view.View;

public class Search extends View {
    @FXML
    private Tab betweenDateSearch;
    @FXML
    private Tab zipCodeSearch;
    @FXML
    private Tab customerRankSearch;
    @FXML
    private Tab ordersFromSelectedCustomer;

    private final String pathToBetweenDates = "/FXML/search/ordersBetweenTwoDates.fxml";
    private final String pathToZipCodeSearch = "/FXML/search/zipcodeSearch.fxml";
    private final String pathToCustomerRankSearch = "/FXML/search/ordersFromCustomerRank.fxml";
    private final String pathToOrdersFromSelectedCustomer = "/FXML/search/ordersFromSelectedCustomer.fxml";

    @Override
    public void init() {
        betweenDateSearch.setOnSelectionChanged(e -> {
            setView(betweenDateSearch, pathToBetweenDates);
        });

        zipCodeSearch.setOnSelectionChanged(e -> {
            setView(zipCodeSearch, pathToZipCodeSearch);
        });

        customerRankSearch.setOnSelectionChanged(e -> {
            setView(customerRankSearch, pathToCustomerRankSearch);
        });

        ordersFromSelectedCustomer.setOnSelectionChanged(e -> {
              setView(ordersFromSelectedCustomer, pathToOrdersFromSelectedCustomer);
        });

        setView(betweenDateSearch, pathToBetweenDates);

    }

    private void setView(Tab tab, String pathToFxml) {
        Parent view;
        try {
            view = FXMLLoader.load(getClass().getResource(pathToFxml));
            tab.setContent(view);
        } catch (Exception e) {
            showError("Loading error", "An error occured while we tried to load the page.");
        }
    }

}
