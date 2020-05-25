package model;

public class Employee {
    private Entity entity;
    private Role role;
    private String password;

    public Employee(Entity entity, Role role, String password) {
        setEntity(entity);
        setRole(role);
        setPassword(password);
    }

    public Employee(Entity entity, Role role) {
        this(entity, role, null);
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public String getPassword() {
        return this.password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return entity.getContactName();
    }
}
