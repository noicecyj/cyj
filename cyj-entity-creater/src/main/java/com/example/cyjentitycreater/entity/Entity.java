package com.example.cyjentitycreater.entity;

public class Entity {
    private Integer id;
    private String entityName;
    private String entityProperty;

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getEntityProperty() {
        return entityProperty;
    }

    public void setEntityProperty(String entityProperty) {
        this.entityProperty = entityProperty;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                ", entityName='" + entityName + '\'' +
                ", entityProperty='" + entityProperty + '\'' +
                '}';
    }
}
