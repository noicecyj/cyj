package com.example.cyjdatadictionary.entity;

        import javax.persistence.*;
        import java.util.Collection;

@Entity
@Table(name = Dictionary.T_DICTIONARY)
public class Dictionary {

    static final String T_DICTIONARY = "t_dictionary";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="pid")
    private DictionaryCatalog dictionaryCatalog;

    public DictionaryCatalog getDictionaryCatalog() {
        return dictionaryCatalog;
    }

    public void setDictionaryCatalog(DictionaryCatalog dictionaryCatalog) {
        this.dictionaryCatalog = dictionaryCatalog;
    }

    @Column
    private String dictionaryName;

    @Column
    private String dictionaryValue;


    public long getId() {
        return id;
    }

    public void setId(long id) {
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

}