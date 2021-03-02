create table t_page_role
(
    page_id varchar(32) not null,
    role_id varchar(32) not null,
    constraint FK1urv1d9q1qmdx87ip837a6vgf
        foreign key (role_id) references t_role (id),
    constraint FK3ucyjyg5uwvyksubdgxpwm4fr
        foreign key (page_id) references t_menu_page (id)
);

