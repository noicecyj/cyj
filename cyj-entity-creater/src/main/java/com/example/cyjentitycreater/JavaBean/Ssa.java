package com.example.cyjentitycreater.JavaBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_Ssa")
public class Ssa {
    @Id
    private Integer asd;

    public Integer getAsd() {
        return asd;
    }

    public void setAsd(Integer asd) {
        this.asd = asd;
    }

}