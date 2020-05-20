package model;

public class Rank {
    private Integer id;
    private String label;
    private Integer creditLimit;
    private Integer minAmountOrder;

    public Rank(Integer id, String label, Integer creditLimit, Integer minAmountOrder){
        setId(id);
        setLabel(label);
        setCreditLimit(creditLimit);
        setMinAmountOrder(minAmountOrder);
    }

    public Rank(Integer id, String label, Integer creditLimit){
        this(id, label, creditLimit, null);
    }

    public Integer getMinAmountOrder() {
        return minAmountOrder;
    }

    public void setMinAmountOrder(Integer minAmountOrder) {
        this.minAmountOrder = minAmountOrder;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setCreditLimit(int creditLimit) {
        if(creditLimit > 0) {
            this.creditLimit = creditLimit;
        } else {
            this.creditLimit = 0;
        }
    }

    public int getId(){
        return this.id;
    }

    public String getLabel(){
        return this.label;
    }

    public int getCreditLimit(){
        return this.creditLimit;
    }

    @Override
    public String toString() {
        return getLabel() + " (" + getCreditLimit() + "€)";
    }
}
