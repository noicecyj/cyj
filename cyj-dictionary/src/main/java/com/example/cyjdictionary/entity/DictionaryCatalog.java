package com.example.cyjdictionary.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = DictionaryCatalog.T_DICTIONARY_CATALOG)
@Data
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
}
