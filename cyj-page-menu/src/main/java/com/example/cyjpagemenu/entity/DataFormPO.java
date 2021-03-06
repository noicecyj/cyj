package com.example.cyjpagemenu.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-12-16
 */
@Entity
@Table(name = DataFormPO.T_DATA_FORM)
@Data
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
public class DataFormPO implements Serializable {

    static final String T_DATA_FORM = "t_data_form";

    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "data_form_name")
    private String dataFormName;

    @Column(name = "description")
    private String description;

    @Column(name = "sort_code")
    private String sortCode;

}