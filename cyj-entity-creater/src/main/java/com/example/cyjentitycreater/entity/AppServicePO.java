package com.example.cyjentitycreater.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-12-24
 */
@Entity
@Table(name = AppServicePO.T_APP_SERVICE)
@Data
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
public class AppServicePO implements Serializable {

    static final String T_APP_SERVICE = "t_app_service";

    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "app_name")
    private String appName;

    @Column(name = "app_api")
    private String appApi;

    @Column(name = "app_port")
    private String appPort;

    @Column(name = "sort_code")
    private String sortCode;

}