package com.example.cyjcommon.entity;

import javax.persistence.*;

@Entity
@Table(name = DictionaryCatalog.T_DICTIONARY_CATALOG)
public class DictionaryCatalog {
	
	static final String T_DICTIONARY_CATALOG = "t_dictionary_catalog";

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String catalogName;

    @Column
    private String catalogValue;

    @Column
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
