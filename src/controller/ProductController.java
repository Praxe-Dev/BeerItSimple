package controller;

import business.ProductBusiness;
import javafx.scene.control.DatePicker;
import model.Product;
import model.ProductIncome;

import java.time.LocalDate;
import java.util.ArrayList;

public class ProductController {
    private ProductBusiness productBusiness;

    public ProductController() {
        this.productBusiness = new ProductBusiness();
    }

    public ArrayList<Product> getAllProducts() {
        return productBusiness.getAllProducts();
    }

    public ArrayList<ProductIncome> getAllProductsIncome(LocalDate startDate, LocalDate endDate) {
        return productBusiness.getAllProductsIncome(startDate, endDate);
    }
}
