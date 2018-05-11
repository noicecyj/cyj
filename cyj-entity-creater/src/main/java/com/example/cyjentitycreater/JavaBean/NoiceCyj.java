package com.example.cyjentitycreater.JavaBean;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_NoiceCyj")
public class NoiceCyj {
    @Id
    private Integer noiceAaa;

    @Column
    private String noiceBbb;

    @Column
    private Date noiceCcc;

    @Column
    private Short noiceDdd;

    public Integer getNoiceAaa() {
        return noiceAaa;
    }

    public void setNoiceAaa(Integer noiceAaa) {
        this.noiceAaa = noiceAaa;
    }

    public String getNoiceBbb() {
        return noiceBbb;
    }

    public void setNoiceBbb(String noiceBbb) {
        this.noiceBbb = noiceBbb;
    }

    public Date getNoiceCcc() {
        return noiceCcc;
    }

    public void setNoiceCcc(Date noiceCcc) {
        this.noiceCcc = noiceCcc;
    }

    public Short getNoiceDdd() {
        return noiceDdd;
    }

    public void setNoiceDdd(Short noiceDdd) {
        this.noiceDdd = noiceDdd;
    }

}