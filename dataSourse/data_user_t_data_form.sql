create table t_data_form
(
    id             varchar(36)  not null
        primary key,
    data_form_name varchar(255) null,
    description    varchar(255) null,
    sort_code      varchar(255) null
);

INSERT INTO data_user.t_data_form (id, data_form_name, description, sort_code) VALUES ('1367bb46-715d-489c-b25f-38a3adfa688b', 'apiForm', '接口表单', '0070');
INSERT INTO data_user.t_data_form (id, data_form_name, description, sort_code) VALUES ('30faf267-2baf-4151-81ba-172e30cf5ada', 'dataFormItemForm', '表单管理子表表单', '0020');
INSERT INTO data_user.t_data_form (id, data_form_name, description, sort_code) VALUES ('3395a2b3-ddf9-4f14-82a1-626b39488f3e', 'dictionaryForm', '字典表单', '0031');
INSERT INTO data_user.t_data_form (id, data_form_name, description, sort_code) VALUES ('36561a8b-58f2-4772-9284-0d955b4ac18f', 'dataTableForm', '表格管理表单', '0030');
INSERT INTO data_user.t_data_form (id, data_form_name, description, sort_code) VALUES ('3d5e0af3-d2ab-456a-b282-89bf2fe9b80f', 'dataFormForm', '表单管理表单', '0010');
INSERT INTO data_user.t_data_form (id, data_form_name, description, sort_code) VALUES ('3f717ee6-3f4f-4c9d-a17b-eba5a25a77a2', 'entityNameForm', '实体', '0010');
INSERT INTO data_user.t_data_form (id, data_form_name, description, sort_code) VALUES ('6706eaec-0d17-47df-8f0b-efbb73a65b6a', 'userForm', '用户表单', '0050');
INSERT INTO data_user.t_data_form (id, data_form_name, description, sort_code) VALUES ('803e507b-8794-4a1e-a0d7-5b3f72170248', 'dataTableItemForm', '表格管理子表表单', '0040');
INSERT INTO data_user.t_data_form (id, data_form_name, description, sort_code) VALUES ('a35845a3-16d0-410c-91e1-e42f75914917', 'pageFunctionForm', null, null);
INSERT INTO data_user.t_data_form (id, data_form_name, description, sort_code) VALUES ('a3f7a6ec-1e1b-467a-b9d7-31ce67d746c3', 'catalogForm', '字典目录表单', '0030');
INSERT INTO data_user.t_data_form (id, data_form_name, description, sort_code) VALUES ('c5f8f521-8cdb-4a97-b4e7-375d11c2b76a', 'roleForm', '角色表单', '0060');
INSERT INTO data_user.t_data_form (id, data_form_name, description, sort_code) VALUES ('c65036e9-c8c7-41dd-a0a4-8fb5b34dbd0b', 'entityForm', '实体子表', '0011');
INSERT INTO data_user.t_data_form (id, data_form_name, description, sort_code) VALUES ('ca9beac2-4ea2-403f-a2fe-dd5f9f42f85f', 'appServiceForm', '服务管理表单', '0050');
INSERT INTO data_user.t_data_form (id, data_form_name, description, sort_code) VALUES ('e7443717-6d83-47ad-80fc-973417f8ae93', 'menuPageForm', '菜单表单', '0040');
INSERT INTO data_user.t_data_form (id, data_form_name, description, sort_code) VALUES ('ef5a4052-3887-46fc-a8e8-ff95e80653dd', 'roleForm', null, null);