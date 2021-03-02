create table t_user_role
(
    user_id varchar(32) not null,
    role_id varchar(32) not null,
    constraint FKa9c8iiy6ut0gnx491fqx4pxam
        foreign key (role_id) references t_role (id),
    constraint FKq5un6x7ecoef5w1n39cop66kl
        foreign key (user_id) references t_user (id)
);

