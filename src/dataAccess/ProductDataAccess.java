package dataAccess;

import exception.DataQueryException;
import model.Product;

import java.util.ArrayList;

public interface ProductDataAccess {
    ArrayList<Product> getAllProducts() throws DataQueryException;
}
