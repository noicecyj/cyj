package com.example.cyjdictionary.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@Entity
@Table(name = DictionaryCatalogPO.T_DICTIONARY_CATALOG)
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@Data
public class DictionaryCatalogPO implements Serializable {

    static final String T_DICTIONARY_CATALOG = "t_dictionary_catalog";

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "id", length = 32)
    private String id;

    @Column(name = "catalog_name")
    private String catalogName;

    @Column(name = "catalog_value")
    private String catalogValue;

    @Column(name = "description")
    private String description;

    @Column(name = "sort_code")
    private String sortCode;
}
