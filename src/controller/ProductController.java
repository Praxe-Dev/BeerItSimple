package controller;

import business.ProductBusiness;
import exception.ConnectionException;
import exception.DataQueryException;
import javafx.scene.control.DatePicker;
import model.Product;
import model.ProductIncome;

import java.time.LocalDate;
import java.util.ArrayList;

public class ProductController {
    private ProductBusiness productBusiness;

    public ProductController() throws ConnectionException {
        this.productBusiness = new ProductBusiness();
    }

    public ArrayList<Product> getAllProducts() throws DataQueryException {
        return productBusiness.getAllProducts();
    }

    public ArrayList<ProductIncome> getAllProductsIncome(LocalDate startDate, LocalDate endDate) throws DataQueryException {
        return productBusiness.getAllProductsIncome(startDate, endDate);
    }
}
