package model;

public class Rank {
    private Integer id;
    private String label;
    private Integer creditLimit;

    public Rank(Integer id, String label, Integer creditLimit){
        setId(id);
        setLabel(label);
        setCreditLimit(creditLimit);
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
