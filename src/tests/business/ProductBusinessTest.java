package business;

import exception.ConnectionException;
import model.Order;
import model.OrderLine;
import model.Product;
import model.ProductIncome;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ProductBusinessTest {
    private ProductBusiness productBusiness;
    ArrayList<Product> allProducts;
    ArrayList<OrderLine> allOrderLines;

    @BeforeEach
    void setUp() {
        try {
            productBusiness = new ProductBusiness();
        } catch (ConnectionException exception) {
            exception.printStackTrace();
        }

        allProducts = new ArrayList<>();
        allOrderLines = new ArrayList<>();
    }

    @Test
    void totalPercentageOneProduct() {
        // one product, one orderline -> 100%
        allProducts.add(new Product(null, 1, "productTest", 10.0, null, null, null,null));
        allOrderLines.add(new OrderLine(new Product(1), null, 2, 10.0));

        ArrayList<ProductIncome> productIncomes = productBusiness.computeProductsIncome(allProducts, allOrderLines);
        productIncomes.get(0).calculPercentage();
        assertEquals(100.0, productIncomes.get(0).getPercentage(), 0.03);
    }

    @Test
    void totalPercentageNoOrderLine() {
        // one product, no orderline -> 0%
        allProducts.add(new Product(null, 1, "productTest", 10.0, null, null, null,null));

        ArrayList<ProductIncome> productIncomes = productBusiness.computeProductsIncome(allProducts, allOrderLines);
        productIncomes.get(0).calculPercentage();
        assertEquals(0.0, productIncomes.get(0).getPercentage(), 0 );
    }

    @Test
    void totalPercentageNoOrderLineForThisProduct() {
        // one product, one orderline but not for the product -> 0%
        allProducts.add(new Product(null, 1, "productTest", 10.0, null, null, null,null));
        allOrderLines.add(new OrderLine(new Product(2), null, 2, 10.0));

        ArrayList<ProductIncome> productIncomes = productBusiness.computeProductsIncome(allProducts, allOrderLines);
        productIncomes.get(0).calculPercentage();
        assertEquals(0.0, productIncomes.get(0).getPercentage(), 0 );
    }
}