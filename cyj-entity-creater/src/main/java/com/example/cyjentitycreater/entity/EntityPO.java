package com.example.cyjentitycreater.entity;

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
 * @date 2021-02-09
 */
@Entity
@Table(name = EntityPO.T_ENTITY)
@Data
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
public class EntityPO implements Serializable {

    static final String T_ENTITY = "t_entity";

    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "pid")
    private String pid;

    @Column(name = "entity_name")
    private String entityName;

    @Column(name = "entity_property")
    private String entityProperty;

    @Column(name = "description")
    private String description;

    @Column(name = "sort_code")
    private String sortCode;

}