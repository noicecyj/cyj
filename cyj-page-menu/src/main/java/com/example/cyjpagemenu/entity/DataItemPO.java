package com.example.cyjpagemenu.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-12-05
 */
@Entity
@Table(name = DataItemPO.T_DATA_ITEM)
@Data
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
public class DataItemPO implements Serializable {

    static final String T_DATA_ITEM = "t_data_item";

    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "pid")
    private String pid;

    @Column(name = "label")
    private String label;

    @Column(name = "required")
    private String required;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "data_source")
    private String dataSource;

    @Column(name = "sort_code")
    private String sortCode;

}