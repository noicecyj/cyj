package com.example.cyjdictionary.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@Entity
@Table(name = Dictionary.T_DICTIONARY)
@Data
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Dictionary {

    static final String T_DICTIONARY = "t_dictionary";

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "id",length = 32)
    private String id;

    @Column(name = "pid",length = 32)
    private String pid;

    @Column(name = "dictionary_name")
    private String dictionaryName;

    @Column(name = "dictionary_value")
    private String dictionaryValue;

    @Column
    private String sortCode;
}