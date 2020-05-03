package model;

import java.time.LocalDate;

public class OrderLineTableFormat {
    private int productCode;
    private String product;
    private double unitPrice;
    private int quantity;
    private double total;

    public OrderLineTableFormat(Product p, int quantity) {
        this.productCode = p.getCode();
        this.product = p.toString();
        this.unitPrice = p.getUnitPrice();
        this.quantity = quantity;
        this.total = unitPrice * (double) quantity;
    }

    public int getProductCode() {
        return productCode;
    }

    public String getProduct() {
        return product;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "OrderLineTableFormat{" +
                "productCode=" + productCode +
                ", product='" + product + '\'' +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", total=" + total +
                '}';
    }
}
