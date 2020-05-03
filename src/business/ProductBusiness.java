package business;

import dataAccess.ProductDBAccess;
import dataAccess.ProductDataAccess;
import model.Product;

import java.util.ArrayList;

public class ProductBusiness {
    private ProductDataAccess dao;

    public ProductBusiness() {
        this.dao = new ProductDBAccess();
    }

    public ArrayList<Product> getAllProducts() {
        return dao.getAllProducts();
    }
}
