create table entity_namepo_rel_entity
(
    entity_namepo_id varchar(36)  not null,
    rel_entity       varchar(255) null,
    constraint FK58dl9meb16el6muno52gjgy4u
        foreign key (entity_namepo_id) references t_entity_name (id)
);

INSERT INTO data_user.entity_namepo_rel_entity (entity_namepo_id, rel_entity) VALUES ('2baaa27c-c5aa-4e6c-81fb-d31cfeccd16c', 'dictionary');