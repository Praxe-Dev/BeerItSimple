package model;

import java.util.GregorianCalendar;

public class Customer {
    private Rank rank;
    private Entity entity;
    private GregorianCalendar date;

    public Customer(Entity entity, Rank rank, GregorianCalendar date) {
        setEntity(entity);
        setRank(rank);
        setDate(date);
    }

    public Customer(Entity entity, Rank rank){
        this(entity, rank, null);
    }

    public Customer(Entity entity, GregorianCalendar date){
        this(entity, null, date);
    }

    public Customer(Entity entity){
        this(entity, null, null);
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public GregorianCalendar getDate() {
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

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public Entity getEntity(){
        return this.entity;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "rank=" + rank.getId() +
                ", entity=" + entity.getId() +
                ", date=" + date +
                '}';
    }
}
