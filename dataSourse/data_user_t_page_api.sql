create table t_page_api
(
    page_id varchar(32) not null,
    api_id  varchar(32) not null,
    constraint FK6gjw85dkf1yfoprr3i147mi1s
        foreign key (api_id) references t_api (id),
    constraint FKpc2gv8bpoombcnnhdjlidjoxo
        foreign key (page_id) references t_menu_page (id)
);

