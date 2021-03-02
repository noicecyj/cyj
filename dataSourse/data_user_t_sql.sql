create table t_sql
(
    id              varchar(36)  not null
        primary key,
    sort_code       varchar(255) null,
    sql_description varchar(255) null,
    sql_str         varchar(255) null,
    sql_type        varchar(255) null
);

INSERT INTO data_user.t_sql (id, sort_code, sql_description, sql_str, sql_type) VALUES ('8db7ee6e-2f0c-438f-94a8-7d32b6444a89', '0001', '1', 'select * from t_dictionary', '查询');
INSERT INTO data_user.t_sql (id, sort_code, sql_description, sql_str, sql_type) VALUES ('deabdefa-598c-4d9e-89a0-ad97d6c11c8c', '0002', '34', 'select * from t_sql', '查询');