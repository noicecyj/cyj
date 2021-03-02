create table t_page_role_function
(
    function_id varchar(32) not null,
    role_id     varchar(32) not null,
    constraint FK8i999aaabbat21wxf03lmtojg
        foreign key (function_id) references t_page_function (id),
    constraint FKqw1012p28aoykbp0yyh1d76g7
        foreign key (role_id) references t_role (id)
);

