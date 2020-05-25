package model;

public class OrderLineTableFormat {
    private int productCode;
    private String product;
    private double unitPrice;
    private int quantity;
    private int VATCodeRate;
    private double exclVat;
    private double inclVat;

    public OrderLineTableFormat(Product p, int quantity) {
        setProductCode(p.getCode());
        setProduct(p.toString());
        setUnitPrice(p.getUnitPrice());
        setQuantity(quantity);
        setVATCodeRate(p.getVATRate());
        setExclVat(quantity);
        setInclVat();
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setVATCodeRate(int VATCodeRate) {
        this.VATCodeRate = VATCodeRate;
    }

    public void setExclVat(int quantity) {
        if(unitPrice > 0){
            this.exclVat = unitPrice * (double) quantity;
        }
    }

    public void setInclVat() {
        if(getExclVat() > 0 && getVatCodeRate() > 0){
            this.inclVat = getExclVat() * ((getVatCodeRate() / 100) +1);
        } else {
            this.inclVat = 0;
        }
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
