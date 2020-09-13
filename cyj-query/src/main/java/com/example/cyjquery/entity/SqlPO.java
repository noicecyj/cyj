package com.example.cyjquery.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-09-13
 */
@Entity
@Table(name = SqlPO.T_SQL)
@Data
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
public class SqlPO implements Serializable {

    static final String T_SQL = "t_sql";

    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "sql_str")
    private String sqlStr;

    @Column(name = "sql_description")
    private String sqlDescription;

    @Column(name = "sql_type")
    private String sqlType;

    @Column(name = "sort_code")
    private String sortCode;

}