package com.example.cyjquery.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-08-30
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

    @Column
    private String sqlStr;

    @Column
    private String sqlDescription;

    @Column
    private String sqlType;

    @Column
    private String sortCode;

}