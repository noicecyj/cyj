package com.example.cyjentitycreater.JavaBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_QweGfdg")
public class QweGfdg {
    @Id
    private Integer asdEqe;

    @Column
    private String zxcSda;

    public Integer getAsdEqe() {
        return asdEqe;
    }

    public void setAsdEqe(Integer asdEqe) {
        this.asdEqe = asdEqe;
    }

    public String getZxcSda() {
        return zxcSda;
    }

    public void setZxcSda(String zxcSda) {
        this.zxcSda = zxcSda;
    }

}