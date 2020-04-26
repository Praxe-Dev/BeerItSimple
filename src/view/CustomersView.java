package view;

import controller.CustomerController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import model.CustomerTableView;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomersView extends VBox implements Initializable {

//    private ObservableList<ObservableList> data;
//    @FXML
//    private TableView<CustomerTableView> customersTable;
    @FXML
    private VBox vbox;
    @FXML
    private Label label;

    /**
     * Variable d'instance
     */
    private CustomerController customersController;

    public CustomersView() {
        this.customersController = new CustomerController();
//        vbox.getStyleClass().add("vbox");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

//        ObservableList<Customer> allCustomers = customersController.getAllCustomers();

        TableView<CustomerTableView> customersTable = new TableView<>();

        ObservableList<CustomerTableView> allCustomer = customersController.getAllCustomers();

        TableColumn<CustomerTableView, Integer> idColumn = new TableColumn<CustomerTableView, Integer>("Id #");
        idColumn.prefWidthProperty().bind(customersTable.widthProperty().multiply(0.1));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<CustomerTableView, String> contactNameColumn = new TableColumn<CustomerTableView, String>("Contact Name");
        contactNameColumn.prefWidthProperty().bind(customersTable.widthProperty().multiply(0.3));
        contactNameColumn.setCellValueFactory(new PropertyValueFactory<>("contactName"));

        TableColumn<CustomerTableView, String> phoneNumberColumn = new TableColumn<CustomerTableView, String>("Phone Number");
        phoneNumberColumn.prefWidthProperty().bind(customersTable.widthProperty().multiply(0.3));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        TableColumn<CustomerTableView, String> mailColumn = new TableColumn<CustomerTableView, String>("Mail");
        mailColumn.prefWidthProperty().bind(customersTable.widthProperty().multiply(0.3));
        mailColumn.setCellValueFactory(new PropertyValueFactory<>("mail"));

        customersTable.setItems(customersController.getAllCustomers());
        customersTable.getColumns().addAll(idColumn, contactNameColumn, phoneNumberColumn, mailColumn);

        int labelIndex = vbox.getChildren().indexOf(label);
        vbox.getChildren().add(labelIndex + 1,customersTable);

//        customersTable.getColumns().setAll(idColumn, contactNameColumn, phoneNumberColumn, mailColumn);





//        for (Customer t : allCustomers) {
//
//        }

//        Connection connection = DBConnection.getDBConnection();
//
//        data = FXCollections.observableArrayList();
//
//        try {
//            String sqlInstruction = "SELECT c.EntityId, e.contactName, e.phoneNumber, e.mail\n" +
//                    "FROM customer c JOIN entity e\n" +
//                    "ON c.EntityId = e.id";
//
//            ResultSet resultData = connection.createStatement().executeQuery(sqlInstruction);
//
//            // Add table column dymanically
//            for (int i = 0; i < resultData.getMetaData().getColumnCount(); i++) {
//                final int j = i;
//                TableColumn col = new TableColumn(resultData.getMetaData().getColumnName(i+1));
//                col.setPrefWidth(200.0);
//                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>, ObservableValue<String>>(){
//                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
//                        return new SimpleStringProperty(param.getValue().get(j).toString());
//                    }
//                });
//
//                customersTable.getColumns().addAll(col);
//                System.out.println("Column ["+i+"] ");
//            }
//
//            while (resultData.next()) {
//                // Iterate row
//                ObservableList<String> row = FXCollections.observableArrayList();
//
//                for (int i = 1; i <= resultData.getMetaData().getColumnCount(); i++) {
//                    // Iterate Column
//                    row.add(resultData.getString(i));
//                }
//
//                System.out.println("Row [1] added " + row);
//                data.add(row);
//            }
//
//            customersTable.setItems(data);
//        } catch (Exception e) {
//            e.getMessage();
//        }
    }
}
