package dataAccess;

import exception.ConnectionException;
import exception.DataQueryException;
import model.Product;
import model.VATCodeRate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDBAccess implements ProductDataAccess {

    private Connection connection;

    public ProductDBAccess() throws ConnectionException {
        this.connection = DBConnection.getInstance();
    }

    @Override
    public ArrayList<Product> getAllProducts() throws DataQueryException {
        String sqlInstruction = "SELECT * FROM product";

        ArrayList<Product> productList = new ArrayList<>();
        Product product;
        VATCodeRate vatCodeRate;

        try {
            ResultSet data = connection.createStatement().executeQuery(sqlInstruction);

            while (data.next()) {
                vatCodeRate = new VATCodeRate( data.getInt("VATCodeRate"));

                product = new Product(
                        null,
                        data.getInt("code"),
                        data.getString("label"),
                        data.getDouble("unitPrice"),
                        data.getInt("currentStock"),
                        data.getInt("maxStock"),
                        data.getInt("minStock"),
                        vatCodeRate.getRate()
                );

                productList.add(product);
            }

        } catch (SQLException e) {
            throw new DataQueryException();
        }

        return productList;
    }
}
