package com.example.cyjlog.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@Entity
@Table(name = ServerPO.T_SERVER)
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
@Data
public class ServerPO {

    static final String T_SERVER = "t_data_server";

    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "server_name")
    private String serverName;

    @Column(name = "server_port")
    private String serverPort;

    @Column(name = "description")
    private String description;

    @Column(name = "sort_code")
    private String sortCode;
}
