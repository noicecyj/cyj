package com.example.cyjentitycreater.entity;

public class Dictionary {

    static final String T_DICTIONARY = "t_dictionary";

    private Integer id;

    private DictionaryCatalog dictionaryCatalog;

    private String dictionaryName;

    private String dictionaryValue;


    public Integer getId() {
        return id;
    }

    public DictionaryCatalog getDictionaryCatalog() {
        return dictionaryCatalog;
    }

    public void setDictionaryCatalog(DictionaryCatalog dictionaryCatalog) {
        this.dictionaryCatalog = dictionaryCatalog;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getDictionaryName() {
        return dictionaryName;
    }

    public void setDictionaryName(String dictionaryName) {
        this.dictionaryName = dictionaryName;
    }


    public String getDictionaryValue() {
        return dictionaryValue;
    }

    public void setDictionaryValue(String dictionaryValue) {
        this.dictionaryValue = dictionaryValue;
    }

    @Override
    public String toString() {
        return "Dictionary{" +
                "id=" + id +
                ", dictionaryCatalog=" + dictionaryCatalog +
                ", dictionaryName='" + dictionaryName + '\'' +
                ", dictionaryValue='" + dictionaryValue + '\'' +
                '}';
    }
}