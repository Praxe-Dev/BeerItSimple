package model;

public class Customer {
    private Rank rank;
    private Entity entity;
    private String date;

    public Customer(Entity entity, Rank rank, String date) {
        setEntity(entity);
        setRank(rank);
        setDate(date);
    }

    public Customer(Entity entity, Rank rank){
        this(entity, rank, null);
    }

    public Customer(Entity entity, String date){
        this(entity, null, date);
    }

    public Customer(Entity entity){
        this(entity, null, null);
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public String getDate() {
        return date;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Rank getRank() {
        return this.rank;
    }

    public void setRankId(Rank rank) {
        this.rank = rank;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Entity getEntity(){
        return this.entity;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "rank =" + getRank() +
                ", date ='" + date + '\'' +
                '}';
    }
}
