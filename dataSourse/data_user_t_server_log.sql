create table t_server_log
(
    id          varchar(255) not null
        primary key,
    app_port    varchar(255) null,
    class       varchar(255) null,
    classpath   varchar(255) null,
    create_date varchar(255) null,
    method      varchar(255) null,
    msg         varchar(255) null,
    msg_level   varchar(255) null,
    thread_name varchar(255) null
);