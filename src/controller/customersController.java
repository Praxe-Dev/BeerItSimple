package controller;

import dataAccess.DBConnection;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class customersController implements Initializable {

    private ObservableList<ObservableList> data;

    @FXML
    private TableView customersTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connection connection = DBConnection.getDBConnection();

        data = FXCollections.observableArrayList();

        try {
            String sqlInstruction = "SELECT c.EntityId, e.contactName, e.phoneNumber, e.mail\n" +
                                    "FROM customer c JOIN entity e\n" +
                                    "ON c.EntityId = e.id";

            ResultSet resultData = connection.createStatement().executeQuery(sqlInstruction);

            // Add table column dymanically
            for (int i = 0; i < resultData.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn col = new TableColumn(resultData.getMetaData().getColumnName(i+1));
                col.setPrefWidth(200.0);
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                customersTable.getColumns().addAll(col);
                System.out.println("Column ["+i+"] ");
            }

            while (resultData.next()) {
                // Iterate row
                ObservableList<String> row = FXCollections.observableArrayList();

                for (int i = 1; i <= resultData.getMetaData().getColumnCount(); i++) {
                    // Iterate Column
                    row.add(resultData.getString(i));
                }

                System.out.println("Row [1] added " + row);
                data.add(row);
            }

            customersTable.setItems(data);
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
