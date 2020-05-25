package business;

import dataAccess.OrderLineDBAccess;
import dataAccess.OrderLineDataAccess;
import dataAccess.ProductDBAccess;
import dataAccess.ProductDataAccess;
import exception.ConnectionException;
import exception.DataQueryException;
import exception.NullObjectException;
import model.OrderLine;
import model.Product;
import model.ProductIncome;

import java.time.LocalDate;
import java.util.ArrayList;

public class ProductBusiness {
    private ProductDataAccess productDao;
    private OrderLineDataAccess orderLineDao;

    public ProductBusiness() throws ConnectionException {
        this.productDao = new ProductDBAccess();
        this.orderLineDao = new OrderLineDBAccess();
    }

    public ArrayList<Product> getAllProducts() throws DataQueryException {
        return productDao.getAllProducts();
    }

    public ArrayList<ProductIncome> getAllProductsIncome(LocalDate startDate, LocalDate endDate) throws DataQueryException, NullObjectException {
        if (startDate == null || endDate == null)
            throw new NullObjectException(startDate.getClass().getName());

        ArrayList<Product> allProducts = productDao.getAllProducts();
        ArrayList<OrderLine> allOrderlines = orderLineDao.getAllOrderLineBetweenDates(startDate, endDate);

        return computeProductsIncome(allProducts, allOrderlines);
    }

    public ArrayList<ProductIncome> computeProductsIncome(ArrayList<Product> allProducts, ArrayList<OrderLine> allOrderlines) {
        ArrayList<ProductIncome> productIncomes = new ArrayList<>();
        ProductIncome.resetTotalSold();
        int index;

        for (Product p : allProducts) {
            productIncomes.add(new ProductIncome(p));
            index = productIncomes.size() - 1;

//            allOrderlines.forEach(x -> System.out.println(x.getProduct().getCode()));

            allOrderlines.stream()
                    .filter(x -> x.getProduct().getCode().equals(p.getCode()))
                    .forEach(productIncomes.get(index)::addAmountSold);
        }

        productIncomes.forEach(ProductIncome::calculPercentage);

        return productIncomes;
    }
}
