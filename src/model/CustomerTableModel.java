package model;

import controller.CustomerController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class CustomerTableModel {
    private List<CustomerTableFormat> list;
    private ObservableList<CustomerTableFormat> row;
    private Collection<TableColumn<CustomerTableFormat, String>> column;

    public CustomerTableModel() {
        list = new ArrayList<>();
        CustomerController customerController = new CustomerController();
        ArrayList<Customer> customerList = customerController.getAllCustomers();
        for (Customer c : customerList) {
            list.add(new CustomerTableFormat(c));
        }

        row = FXCollections.observableArrayList(list);
        column = new ArrayList<>();
        this.initColumn();
    }

    private void initColumn() {
        column.add(this.addTableColumn("ID #", "id"));
        column.add(this.addTableColumn("Name", "contactName"));
        column.add(this.addTableColumn("Phone #", "phoneNumber"));
        column.add(this.addTableColumn("Address", "address"));
        column.add(this.addTableColumn("Bank Account", "bankAccountNumber"));
        column.add(this.addTableColumn("businessNumber", "businessNumber"));
        column.add(this.addTableColumn("VATNumber", "VATNumber"));
        column.add(this.addTableColumn("cityLabel", "cityLabel"));
        column.add(this.addTableColumn("zipCode", "zipCode"));
        column.add(this.addTableColumn("rankLabel", "rankLabel"));
        column.add(this.addTableColumn("creditLimit", "creditLimit"));
    }

    private TableColumn addTableColumn(String tableHeader, String entityAttribute) {
        TableColumn t = new TableColumn(tableHeader);
        t.setCellFactory(new PropertyValueFactory<CustomerTableFormat, String>(entityAttribute));
        return t;
    }

    public Collection<TableColumn<CustomerTableFormat, String>> getAllColumn() {
        return column;
    }

    public ObservableList<CustomerTableFormat> getItem() {
        return row;
    }
}
