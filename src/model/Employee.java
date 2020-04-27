package model;

public class Employee {
    private Integer id;
    private Integer roleId;
    private String password;
    private Entity entity;

    public Employee(Integer id, Integer roleId, String password, Entity entity) {
        this.id = id;
        this.roleId = roleId;
        this.password = password;
        this.entity = entity;
    }

    public Employee(Integer id, Integer roleId, String password) {
        this(id, roleId, password, null);
    }

    public Employee (Integer id, String password) {
        this(id, null, password, null);
    }

    public int getId() {
        return this.id;
    }

    public String getPassword() {
        return this.password;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", password='" + password + '\'' +
                ", entity=" + entity +
                '}';
    }
}
