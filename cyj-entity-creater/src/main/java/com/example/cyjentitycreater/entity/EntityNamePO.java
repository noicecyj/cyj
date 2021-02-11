package com.example.cyjentitycreater.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2021-02-09
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

    @Column(name = "name")
    private String name;

    @Column(name = "app_name")
    private String appName;

    @Column(name = "type")
    private String type;

    @Column(name = "path")
    private String path;

    @Column(name = "api")
    private String api;

    @Column(name = "rel_entity")
    private String relEntity;

    @Column(name = "form_model_code")
    private String formModelCode;

    @Column(name = "table_model_code")
    private String tableModelCode;

    @Column(name = "sort_code")
    private String sortCode;

}