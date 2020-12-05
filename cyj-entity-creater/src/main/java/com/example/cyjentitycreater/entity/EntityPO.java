package com.example.cyjentitycreater.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-11-17
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

    @Column(name = "sort_code")
    private String sortCode;

}