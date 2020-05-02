package model;

public class PaymentMethod {
    private Integer id;
    private String label;

    public PaymentMethod(Integer id, String label){
        setId(id);
        setLabel(label);
    }

    public Integer getId(){
        return this.id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getLabel(){
        return this.label;
    }

    public void setLabel(String label){
        this.label = label;
    }
}
