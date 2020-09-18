package com.example.cyjpagemenu.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@Data
public class DictionaryDTO implements Serializable {

    private String id;
    private String pid;
    private String dictionaryName;
    private String dictionaryValue;
    private String sortCode;
}