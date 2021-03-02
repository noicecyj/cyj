create table t_page_function
(
    id           varchar(36)  not null
        primary key,
    key_function varchar(255) null,
    name         varchar(255) null,
    page_id      varchar(32)  null,
    status       int          null,
    sort_code    varchar(255) null
);

