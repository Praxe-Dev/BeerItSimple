package model;

public class Product {
    private Provider provider;
    private Integer code;
    private String label;
    private Double unitPrice;
    private Integer currentStock;
    private Integer maxStock;
    private Integer minStock;
    private Integer VATRate;

    public Product(Provider provider, Integer code, String label, Double unitPrice, Integer currentStock, Integer maxStock, Integer minStock, Integer VATRate) {
        setProvider(provider);
        setCode(code);
        setLabel(label);
        setUnitPrice(unitPrice);
        setCurrentStock(currentStock);
        setMaxStock(maxStock);
        setMinStock(minStock);
        setVATRate(VATRate);
    }

    public Product(Integer code) {
        this(null, code, null, null, null, null, null, null);
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(Integer currentStock) {
        this.currentStock = currentStock;
    }

    public Integer getMaxStock() {
        return maxStock;
    }

    public void setMaxStock(Integer maxStock) {
        this.maxStock = maxStock;
    }

    public Integer getMinStock() {
        return minStock;
    }

    public void setMinStock(Integer minStock) {
        this.minStock = minStock;
    }

    public Integer getVATRate() {
        return VATRate;
    }

    public void setVATRate(Integer VATRate) {
        this.VATRate = VATRate;
    }

    @Override
    public String toString() {
        return "[" + code + "] " + label + " (" + unitPrice + " €)" ;
    }
}
