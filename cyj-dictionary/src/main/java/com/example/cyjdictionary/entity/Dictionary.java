package com.example.cyjdictionary.entity;

import lombok.Data;

import javax.annotation.sql.DataSourceDefinition;
import javax.persistence.*;

@Entity
@Table(name = Dictionary.T_DICTIONARY)
@Data
public class Dictionary {

    static final String T_DICTIONARY = "t_dictionary";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "pid")
    private Integer pid;

    @Column(name = "dictionary_name")
    private String dictionaryName;

    @Column(name = "dictionary_value")
    private String dictionaryValue;
}