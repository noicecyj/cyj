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
@Table(name = DictionaryPO.T_DICTIONARY)
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
@Data
public class DictionaryPO implements Serializable {

    static final String T_DICTIONARY = "t_dictionary";

    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "pid", length = 36)
    private String pid;

    @Column(name = "dictionary_name")
    private String dictionaryName;

    @Column(name = "dictionary_value")
    private String dictionaryValue;

    @Column(name = "sort_code")
    private String sortCode;
}