package com.example.cyjpagemenu.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-12-07
 */
@Entity
@Table(name = DataTablePO.T_DATA_TABLE)
@Data
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
public class DataTablePO implements Serializable {

    static final String T_DATA_TABLE = "t_data_table";

    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "data_table_name")
    private String dataTableName;

    @Column(name = "data_source")
    private String dataSource;

    @Column(name = "is_main")
    private String isMain;

    @Column(name = "sort_code")
    private String sortCode;

}