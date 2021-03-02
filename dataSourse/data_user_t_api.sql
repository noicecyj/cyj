create table t_api
(
    id          varchar(36)  not null
        primary key,
    name        varchar(255) null,
    path        varchar(255) null,
    status      int          null,
    sort_code   varchar(255) null,
    description varchar(255) null
);

INSERT INTO data_user.t_api (id, name, path, status, sort_code, description) VALUES ('f5727f09-ede1-4df8-ba4a-8745f09d7a4c', '21312432', '4234', null, '0010', '123');