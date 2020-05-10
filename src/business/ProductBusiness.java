package business;

import dataAccess.OrderLineDBAccess;
import dataAccess.OrderLineDataAccess;
import dataAccess.ProductDBAccess;
import dataAccess.ProductDataAccess;
import javafx.scene.control.DatePicker;
import model.OrderLine;
import model.Product;
import model.ProductIncome;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ProductBusiness {
    private ProductDataAccess productDao;
    private OrderLineDataAccess orderLineDao;

    public ProductBusiness() {
        this.productDao = new ProductDBAccess();
        this.orderLineDao = new OrderLineDBAccess();
    }

    public ArrayList<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    public ArrayList<ProductIncome> getAllProductsIncome(LocalDate startDate, LocalDate endDate) {
        ArrayList<Product> allProducts = productDao.getAllProducts();
        ArrayList<OrderLine> allOrderlines = orderLineDao.getAllOrderLineBetweenDates(startDate, endDate);

        ArrayList<ProductIncome> productIncomes = new ArrayList<>();

//        for (Product p : allProducts) {
//            productIncomes.add(new ProductIncome(p));
//            int index = productIncomes.size() - 1;
//
//            allOrderlines.stream()
//                    .filter(x -> x.getProduct().getCode().equals(p.getCode()))
//                    .forEach(x -> productIncomes.get(index).addAmountSold(x));
//        }

//        Do the same thing but with Method reference
        for (Product p : allProducts) {
            productIncomes.add(new ProductIncome(p));
            int index = productIncomes.size() - 1;

            allOrderlines.stream()
                    .filter(x -> x.getProduct().getCode().equals(p.getCode()))
                    .forEach(productIncomes.get(index)::addAmountSold);
        }

//        for (ProductIncome p : productIncomes) {
//            p.calculPercentage();
//        }

        productIncomes.forEach(ProductIncome::calculPercentage);

        return productIncomes;
    }
}
