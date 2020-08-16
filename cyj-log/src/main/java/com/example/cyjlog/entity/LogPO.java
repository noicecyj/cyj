package com.example.cyjlog.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@Entity
@Table(name = LogPO.T_LOG)
@Data
public class LogPO {

    static final String T_LOG = "t_server_log";

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "app_port")
    private String appPort;

    @Column(name = "class")
    private String className;

    @Column(name = "classpath")
    private String classpath;

    @Column(name = "method")
    private String method;

    @Column(name = "thread_name")
    private String threadName;

    @Column(name = "msg_level")
    private String msgLevel;

    @Column(name = "msg")
    private String msg;

    @Column(name = "create_date")
    private String createDate;
}
