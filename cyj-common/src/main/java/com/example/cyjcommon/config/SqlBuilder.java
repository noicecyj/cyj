package com.example.cyjcommon.config;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
public class SqlBuilder {
    public static String buildInsertSql() {
        return "INSERT INTO " + "t_server_log" + " (" +
                "id" + ", " +
                "app_port" + ", " +
                "class" + ", " +
                "classpath" + ", " +
                "method" + ", " +
                "thread_name" + ", " +
                "msg_level" + ", " +
                "msg" + ", " +
                "create_date" + ") " +
                "VALUES (?, ?, ? ,?, ?, ?, ?, ?, ?)";
    }
}
