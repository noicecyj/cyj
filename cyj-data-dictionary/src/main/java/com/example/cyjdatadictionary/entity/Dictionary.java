package com.example.cyjdatadictionary.entity;

        import javax.persistence.*;

@Entity
@Table(name = Dictionary.T_DICTIONARY)
public class Dictionary {

    static final String T_DICTIONARY = "t_dictionary";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "Id")
    private long pid;

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


    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
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