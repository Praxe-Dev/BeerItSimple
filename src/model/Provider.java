package model;

public class Provider {
    private Entity entity;
    private String providerType;

    public Provider(Entity entity, String providerType){
        setEntity(entity);
        setProviderType(providerType);
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public String getProviderType() {
        return providerType;
    }

    public void setProviderType(String providerType) {
        this.providerType = providerType;
    }
}
