create table t_data_table
(
    id              varchar(36)  not null
        primary key,
    data_table_name varchar(255) null,
    description     varchar(255) null,
    sort_code       varchar(255) null
);

INSERT INTO data_user.t_data_table (id, data_table_name, description, sort_code) VALUES ('2e9bf77d-ed51-407d-aa8b-8e38d64b7bb6', 'dataTableTable', '表格管理表格', '0030');
INSERT INTO data_user.t_data_table (id, data_table_name, description, sort_code) VALUES ('3cb83c39-8e26-4110-a613-36e2b40a28de', 'userTable', '用户表格', '0050');
INSERT INTO data_user.t_data_table (id, data_table_name, description, sort_code) VALUES ('45ba8cfa-3cb0-46f1-86df-eaad49e783ff', 'roleTable', '角色表格', '0060');
INSERT INTO data_user.t_data_table (id, data_table_name, description, sort_code) VALUES ('46bd928a-0d5c-4e9c-948e-bfcc408e6e60', 'roleTable', null, null);
INSERT INTO data_user.t_data_table (id, data_table_name, description, sort_code) VALUES ('54bfc84f-e5f9-4e15-840c-3907b54ccc76', 'dataFormItemTable', '表单管理子表表格', '0020');
INSERT INTO data_user.t_data_table (id, data_table_name, description, sort_code) VALUES ('59d64df5-fc3c-438c-aa3b-07e833788df5', 'menuPageTable', '菜单表格', '0050');
INSERT INTO data_user.t_data_table (id, data_table_name, description, sort_code) VALUES ('715294ef-2877-486d-b227-1226bdd442e5', 'appServiceTable', '服务管理表格', '0050');
INSERT INTO data_user.t_data_table (id, data_table_name, description, sort_code) VALUES ('7b4203a9-02a5-441b-bd4c-128bc72046d4', 'apiTable', '接口表格', '0070');
INSERT INTO data_user.t_data_table (id, data_table_name, description, sort_code) VALUES ('837df934-7fbc-4449-8fd3-f08da07a42b1', 'dictionaryTable', '字典表格', '0031');
INSERT INTO data_user.t_data_table (id, data_table_name, description, sort_code) VALUES ('869b4f7e-ef59-4637-9256-90391cb7d2c8', 'dataFormTable', '表单管理表单', '0010');
INSERT INTO data_user.t_data_table (id, data_table_name, description, sort_code) VALUES ('8c8be183-8b47-42c9-a65c-844d099073bf', 'entityTable', '实体子表', '0011');
INSERT INTO data_user.t_data_table (id, data_table_name, description, sort_code) VALUES ('8f096850-d582-4d9d-b1d9-cf4f9c4b3e0d', 'entityNameTable', '实体', '0010');
INSERT INTO data_user.t_data_table (id, data_table_name, description, sort_code) VALUES ('97a6de0f-eea5-4bfc-adb4-de8204864793', 'pageFunctionTable', null, null);
INSERT INTO data_user.t_data_table (id, data_table_name, description, sort_code) VALUES ('b0e5ce46-1dc8-43d6-afc8-c4d4ca87bed2', 'dataTableItemTable', '表格管理子表表格', '0040');
INSERT INTO data_user.t_data_table (id, data_table_name, description, sort_code) VALUES ('e801febb-dadd-42be-836c-bfc699db0c04', 'catalogTable', '字典目录表格', '0030');