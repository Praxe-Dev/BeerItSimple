package controller;

import business.ProductBusiness;
import model.Product;

import java.util.ArrayList;

public class ProductController {
    private ProductBusiness productBusiness;

    public ProductController() {
        this.productBusiness = new ProductBusiness();
    }

    public ArrayList<Product> getAllProducts() {
        return productBusiness.getAllProducts();
    }
}
