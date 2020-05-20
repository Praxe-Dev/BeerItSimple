package model;

public class Role {
    private Integer id;
    private String name;

    public Role(Integer id, String name){
        setId(id);
        setName(name);
    }

    public Role(){
        this(null, null);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
