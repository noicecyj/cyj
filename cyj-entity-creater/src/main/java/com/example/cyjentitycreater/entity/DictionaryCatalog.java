package com.example.cyjentitycreater.entity;

public class DictionaryCatalog {

    private Integer id;

    private String catalogName;

    private String catalogValue;

    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }


    public String getCatalogValue() {
        return catalogValue;
    }

    public void setCatalogValue(String catalogValue) {
        this.catalogValue = catalogValue;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "DictionaryCatalog{" +
                "id=" + id +
                ", catalogName='" + catalogName + '\'' +
                ", catalogValue='" + catalogValue + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
