package model;

public class Rank {
    private int id;
    private String label;
    private int creditLimit;

    public Rank(int id, String label, int creditLimit){
        this.id = id;
        this.label = label;
        this.creditLimit = creditLimit;
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
        return "Rank{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", creditLimit=" + creditLimit +
                '}';
    }
}
