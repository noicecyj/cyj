package com.example.cyjdatadictionary.entity;

import javax.persistence.*;

@Entity
@Table(name = DictionaryCatalog.T_DICTIONARY_CATALOG)
public class DictionaryCatalog {
	
	static final String T_DICTIONARY_CATALOG = "t_dictionary_catalog";

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "catalog_name")
    private String catalogName;

    @Column(name = "catalog_value")
    private String catalogValue;

    @Column(name = "description")
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

}
