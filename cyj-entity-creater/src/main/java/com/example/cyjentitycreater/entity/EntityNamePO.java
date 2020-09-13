package com.example.cyjentitycreater.entity;

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
@Table(name = EntityNamePO.T_ENTITY_NAME)
@Data
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
public class EntityNamePO implements Serializable {

    static final String T_ENTITY_NAME = "t_entity_name";

    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(name = "id", length = 36)
    private String id;

    @Column
    private String name;

    @Column
    private String type;

    @Column
    private String path;

    @Column
    private String api;

    @Column
    private String sortCode;

}