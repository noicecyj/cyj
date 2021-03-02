create table t_role
(
    id          varchar(36)  not null
        primary key,
    description varchar(255) null,
    name        varchar(255) null,
    sort_code   varchar(255) null,
    status      int          null
);

INSERT INTO data_user.t_role (id, description, name, sort_code, status) VALUES ('613cd04b-b988-46d6-af34-76c6e3abdde6', '23123', '12312', '0010', 1);