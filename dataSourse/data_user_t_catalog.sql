create table t_catalog
(
    id            varchar(36)  not null
        primary key,
    catalog_name  varchar(255) null,
    catalog_value varchar(255) null,
    description   varchar(255) null,
    sort_code     varchar(255) null
);

INSERT INTO data_user.t_catalog (id, catalog_name, catalog_value, description, sort_code) VALUES ('08f8ed34-1028-4580-b542-8ca45ba3d61c', '前端层级代码', 'LEVEL_ENTITY_TYPE_FOUNT', '前端层级代码', '0009');
INSERT INTO data_user.t_catalog (id, catalog_name, catalog_value, description, sort_code) VALUES ('16a7313b-a159-4766-bdf8-597d4af691a4', '后端层级实体类型', 'LEVEL_ENTITY_TYPE', '后端层级实体类型', '0008');
INSERT INTO data_user.t_catalog (id, catalog_name, catalog_value, description, sort_code) VALUES ('4028818373f751af0173f75b3ec80000', '文件路径', 'FILE_PATH', '生成文件路径', '0005');
INSERT INTO data_user.t_catalog (id, catalog_name, catalog_value, description, sort_code) VALUES ('402881857353170801735321e3f30000', '实体类型', 'ENTITY_TYPE', '实体类种类', '0001');
INSERT INTO data_user.t_catalog (id, catalog_name, catalog_value, description, sort_code) VALUES ('4028818573531708017353228dad0001', '是或否', 'YES_NO', '是或否', '0002');
INSERT INTO data_user.t_catalog (id, catalog_name, catalog_value, description, sort_code) VALUES ('4028818573531708017353282db10003', '数据类型', 'DATA_TYPE', '数据类型', '0004');
INSERT INTO data_user.t_catalog (id, catalog_name, catalog_value, description, sort_code) VALUES ('a4f846e4-47f7-4f08-9f17-0941e2e0907e', 'sql类型', 'SQL_TYPE', 'sql的执行类型', '0006');
INSERT INTO data_user.t_catalog (id, catalog_name, catalog_value, description, sort_code) VALUES ('afa82144-6b85-44b8-b303-c2c63b26e168', '组件状态', 'IS_COMPONENT', '组件状态', '0007');