package com.example.cyjpagemenu.entity;

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
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@Data
public class DictionaryPO implements Serializable {

    static final String T_DICTIONARY = "t_dictionary";

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "id", length = 32)
    private String id;

    @Column(name = "pid", length = 32)
    private String pid;

    @Column(name = "dictionary_name")
    private String dictionaryName;

    @Column(name = "dictionary_value")
    private String dictionaryValue;

    @Column(name = "sort_code")
    private String sortCode;
}