package com.example.cyjentitycreater.entity;

public class Entity {
    private String entityName;
    private String entityProperty;
    private String entityAuto;

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

    public String getEntityAuto() {
        return entityAuto;
    }

    public void setEntityAuto(String entityAuto) {
        this.entityAuto = entityAuto;
    }

    @Override
    public String toString() {
        return "entity{" +
                "entityName='" + entityName + '\'' +
                ", entityProperty='" + entityProperty + '\'' +
                ", entityAuto='" + entityAuto + '\'' +
                '}';
    }
}
