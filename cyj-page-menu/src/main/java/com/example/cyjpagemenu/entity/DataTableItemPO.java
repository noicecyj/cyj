package com.example.cyjpagemenu.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-12-20
 */
@Entity
@Table(name = DataTableItemPO.T_DATA_TABLE_ITEM)
@Data
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
public class DataTableItemPO implements Serializable {

    static final String T_DATA_TABLE_ITEM = "t_data_table_item";

    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "pid")
    private String pid;

    @Column(name = "json_data")
    private String jsonData;

    @Column(name = "sort_code")
    private String sortCode;

}