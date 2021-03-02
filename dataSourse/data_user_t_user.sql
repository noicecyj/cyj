create table t_user
(
    id        varchar(36)  not null
        primary key,
    name      varchar(255) null,
    password  varchar(255) null,
    phone     varchar(255) null,
    sort_code varchar(255) null,
    user_name varchar(255) null,
    status    int          null
);

INSERT INTO data_user.t_user (id, name, password, phone, sort_code, user_name, status) VALUES ('40f07e85-5e7c-43de-8569-d7bc4e445fdb', '23423', '23423', '234234', '0010', '123123', 1);
INSERT INTO data_user.t_user (id, name, password, phone, sort_code, user_name, status) VALUES ('7934c0af-847d-4adb-a63a-1f22102799b6', null, '123123', null, '0010', '123123', null);