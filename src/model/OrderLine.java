package model;

public class OrderLine {
    private Product product;
    private Order order;
    private Integer quantity;
    private Double salesUnitPrice;

    public OrderLine(Product product, Order order, Integer quantity, Double salesUnitPrice){
        setProduct(product);
        setOrder(order);
        setQuantity(quantity);
        setSalesUnitPrice(salesUnitPrice);
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getSalesUnitPrice() {
        return salesUnitPrice;
    }

    public void setSalesUnitPrice(Double salesUnitPrice) {
        this.salesUnitPrice = salesUnitPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getFormatedTotal() {
        double total = salesUnitPrice * quantity;
        return String.format("%.2f €", total);
    }
}
