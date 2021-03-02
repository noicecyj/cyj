create table t_function_api
(
    function_id varchar(32) not null,
    api_id      varchar(32) not null,
    constraint FK9vxj9gj139yqjnu27pngrbi04
        foreign key (function_id) references t_page_function (id),
    constraint FKdp37f03ci46fwsd668n8stsrc
        foreign key (api_id) references t_api (id)
);

