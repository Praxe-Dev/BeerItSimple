package model;

import java.time.LocalDate;

public class OrderLineTableFormat {
    private int productCode;
    private String product;
    private double unitPrice;
    private int quantity;
    private int VATCodeRate;
    private double exclVat;
    private double inclVat;

    public OrderLineTableFormat(Product p, int quantity) {
        this.productCode = p.getCode();
        this.product = p.toString();
        this.unitPrice = p.getUnitPrice();
        this.quantity = quantity;
        this.VATCodeRate = p.getVATRate();
        this.exclVat = unitPrice * (double) quantity;
        this.inclVat = this.exclVat * (((double) VATCodeRate / 100) + 1);
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

    public double getInclVat() {
        // if not casted, not correct (I wanna die)
        return (double) Math.round(inclVat * 100) / 100;
    }
    public double getExclVat() {
        return exclVat;
    }
    public double getVatCodeRate() { return VATCodeRate; }

    @Override
    public String toString() {
        return "OrderLineTableFormat{" +
                "productCode=" + productCode +
                ", product='" + product + '\'' +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", VATCodeRate=" + VATCodeRate +
                ", exclVat=" + exclVat +
                ", inclVat=" + inclVat +
                '}';
    }
}
