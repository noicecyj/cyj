create table t_data_table_item
(
    id        varchar(36)  not null
        primary key,
    json_data varchar(255) null,
    pid       varchar(255) null,
    sort_code varchar(255) null
);

INSERT INTO data_user.t_data_table_item (id, json_data, pid, sort_code) VALUES ('034e032b-07b6-4c4e-b00a-5543c111b626', '{"title":"表单名称","dataIndex":"dataFormName"}', '869b4f7e-ef59-4637-9256-90391cb7d2c8', '0010');
INSERT INTO data_user.t_data_table_item (id, json_data, pid, sort_code) VALUES ('0806f8ca-4de4-4ac1-aab8-f5682a3767d5', '{"title":"状态","dataIndex":"status"}', '3cb83c39-8e26-4110-a613-36e2b40a28de', '0040');
INSERT INTO data_user.t_data_table_item (id, json_data, pid, sort_code) VALUES ('09097b19-62b9-415b-83ed-e6adddb5101c', '{"title":"表格名称","dataIndex":"dataTableName"}', '2e9bf77d-ed51-407d-aa8b-8e38d64b7bb6', '0010');
INSERT INTO data_user.t_data_table_item (id, json_data, pid, sort_code) VALUES ('09ba96bc-cb43-49b9-9106-0704a05ef744', '{"title":"用户名","dataIndex":"userName"}', '3cb83c39-8e26-4110-a613-36e2b40a28de', '0010');
INSERT INTO data_user.t_data_table_item (id, json_data, pid, sort_code) VALUES ('1242c048-b9d1-4fda-882b-7df8258a476f', '{"title":"JSON数据","dataIndex":"jsonData"}', 'b0e5ce46-1dc8-43d6-afc8-c4d4ca87bed2', '0010');
INSERT INTO data_user.t_data_table_item (id, json_data, pid, sort_code) VALUES ('18e48f21-2001-46bb-8bd8-2e3d5bf4adc4', '{"title":"菜单图标","dataIndex":"icon"}', '59d64df5-fc3c-438c-aa3b-07e833788df5', '0060');
INSERT INTO data_user.t_data_table_item (id, json_data, pid, sort_code) VALUES ('26155314-14b6-48a9-aefc-33e8c4e4e4af', '{"title":"名称","dataIndex":"name"}', '7b4203a9-02a5-441b-bd4c-128bc72046d4', '0020');
INSERT INTO data_user.t_data_table_item (id, json_data, pid, sort_code) VALUES ('336a42a6-9870-4806-9a98-edc19dc944b9', '{"title":"数据类型","dataIndex":"entityProperty"}', '8c8be183-8b47-42c9-a65c-844d099073bf', '0011');
INSERT INTO data_user.t_data_table_item (id, json_data, pid, sort_code) VALUES ('338668d1-ad32-4df8-8f74-158a551003e2', '{"title":"菜单代号","dataIndex":"id"}', '59d64df5-fc3c-438c-aa3b-07e833788df5', '0010');
INSERT INTO data_user.t_data_table_item (id, json_data, pid, sort_code) VALUES ('42661728-16ee-4b3f-953e-b72b6861b495', '{"title":"表单模板","dataIndex":"formModelCode"}', '8f096850-d582-4d9d-b1d9-cf4f9c4b3e0d', '0012');
INSERT INTO data_user.t_data_table_item (id, json_data, pid, sort_code) VALUES ('4ac1fbe0-c73e-41af-b76d-e1627806e02c', '{"title":"服务名称","dataIndex":"appName","dataSource":"t_app_service","dataSourceType":"dataBase"}', '8f096850-d582-4d9d-b1d9-cf4f9c4b3e0d', '0011');
INSERT INTO data_user.t_data_table_item (id, json_data, pid, sort_code) VALUES ('55ee3b0a-459a-4eda-8dc1-cb62cc79ed09', '{"title":"菜单路径","dataIndex":"path"}', '59d64df5-fc3c-438c-aa3b-07e833788df5', '0030');
INSERT INTO data_user.t_data_table_item (id, json_data, pid, sort_code) VALUES ('5b9c8db2-794f-4984-b7ee-d0af467e0345', '{"title":"JSON数据","dataIndex":"jsonData"}', '54bfc84f-e5f9-4e15-840c-3907b54ccc76', '0010');
INSERT INTO data_user.t_data_table_item (id, json_data, pid, sort_code) VALUES ('62f1c12f-03d2-4f6d-9ac5-586b1de09609', '{"title":"状态","dataIndex":"status"}', '45ba8cfa-3cb0-46f1-86df-eaad49e783ff', '0040');
INSERT INTO data_user.t_data_table_item (id, json_data, pid, sort_code) VALUES ('67b02cf7-6c32-488b-96db-1d6c2bb0338a', '{"title":"目录描述","dataIndex":"description"}', 'e801febb-dadd-42be-836c-bfc699db0c04', '0030');
INSERT INTO data_user.t_data_table_item (id, json_data, pid, sort_code) VALUES ('75b91d96-0ae9-44ea-81b3-05007eacd3d5', '{"title":"描述","dataIndex":"description"}', '45ba8cfa-3cb0-46f1-86df-eaad49e783ff', '0030');
INSERT INTO data_user.t_data_table_item (id, json_data, pid, sort_code) VALUES ('770bf71a-3a26-413b-99f6-50d47c030698', '{"title":"属性名","dataIndex":"entityName"}', '8c8be183-8b47-42c9-a65c-844d099073bf', '0010');
INSERT INTO data_user.t_data_table_item (id, json_data, pid, sort_code) VALUES ('797fd628-cc88-404c-a0de-dc8c75725a29', '{"title":"姓名","dataIndex":"name"}', '3cb83c39-8e26-4110-a613-36e2b40a28de', '0020');
INSERT INTO data_user.t_data_table_item (id, json_data, pid, sort_code) VALUES ('7da28342-6dd7-4702-920e-de086ecd6804', '{"title":"服务端口","dataIndex":"appPort"}', '715294ef-2877-486d-b227-1226bdd442e5', '0012');
INSERT INTO data_user.t_data_table_item (id, json_data, pid, sort_code) VALUES ('80b1b6be-5823-440f-aa3d-3570a1a4b73f', '{"title":"接口名称","dataIndex":"appApi"}', '715294ef-2877-486d-b227-1226bdd442e5', '0011');
INSERT INTO data_user.t_data_table_item (id, json_data, pid, sort_code) VALUES ('8887ce24-030c-40fb-9302-05abbe7fabf2', '{"title":"电话","dataIndex":"phone"}', '3cb83c39-8e26-4110-a613-36e2b40a28de', '0030');
INSERT INTO data_user.t_data_table_item (id, json_data, pid, sort_code) VALUES ('8c24cff5-0f84-4cff-a7a5-99617d3b1f4f', '{"title":"名称","dataIndex":"name"}', '46bd928a-0d5c-4e9c-948e-bfcc408e6e60', '0020');
INSERT INTO data_user.t_data_table_item (id, json_data, pid, sort_code) VALUES ('9a7b17c0-3bfd-4c56-aefa-a6a60b64f120', '{"title":"描述","dataIndex":"description"}', '869b4f7e-ef59-4637-9256-90391cb7d2c8', '0020');
INSERT INTO data_user.t_data_table_item (id, json_data, pid, sort_code) VALUES ('a0b7f4db-24fe-4ea5-8d30-2cebcd058c9f', '{"title":"目录名称","dataIndex":"catalogName"}', 'e801febb-dadd-42be-836c-bfc699db0c04', '0010');
INSERT INTO data_user.t_data_table_item (id, json_data, pid, sort_code) VALUES ('a338dfdb-843a-44a4-ac2c-834abbdaf67b', '{"title":"组件名称","dataIndex":"componentName"}', '59d64df5-fc3c-438c-aa3b-07e833788df5', '0040');
INSERT INTO data_user.t_data_table_item (id, json_data, pid, sort_code) VALUES ('aae4d437-8bf9-423f-9b8c-304332ab4dd5', '{"title":"描述","dataIndex":"description"}', '2e9bf77d-ed51-407d-aa8b-8e38d64b7bb6', '0020');
INSERT INTO data_user.t_data_table_item (id, json_data, pid, sort_code) VALUES ('b1e0c175-bb23-47f8-a816-d31165fc76a9', '{"title":"名称","dataIndex":"name"}', '45ba8cfa-3cb0-46f1-86df-eaad49e783ff', '0020');
INSERT INTO data_user.t_data_table_item (id, json_data, pid, sort_code) VALUES ('b578c58d-af70-4eed-9805-10eb6ee3233f', '{"title":"实体名称","dataIndex":"name"}', '8f096850-d582-4d9d-b1d9-cf4f9c4b3e0d', '0010');
INSERT INTO data_user.t_data_table_item (id, json_data, pid, sort_code) VALUES ('b928285c-3a30-4ae4-8152-3463a975bd1d', '{"title":"菜单名称","dataIndex":"name"}', '59d64df5-fc3c-438c-aa3b-07e833788df5', '0020');
INSERT INTO data_user.t_data_table_item (id, json_data, pid, sort_code) VALUES ('bcec53b1-0adb-42b8-80ea-c6f2ee1e1f23', '{"title":"表格模板","dataIndex":"tableModelCode"}', '8f096850-d582-4d9d-b1d9-cf4f9c4b3e0d', '0013');
INSERT INTO data_user.t_data_table_item (id, json_data, pid, sort_code) VALUES ('bfba5a54-a957-41f8-a00e-d933f87f4cf1', '{"title":"描述","dataIndex":"description"}', '46bd928a-0d5c-4e9c-948e-bfcc408e6e60', '0030');
INSERT INTO data_user.t_data_table_item (id, json_data, pid, sort_code) VALUES ('d17dfd6b-6753-49b3-b2db-51eece1f3507', '{"title":"值","dataIndex":"dictionaryValue"}', '837df934-7fbc-4449-8fd3-f08da07a42b1', '0020');
INSERT INTO data_user.t_data_table_item (id, json_data, pid, sort_code) VALUES ('d2f205cc-8d60-4e33-a000-01653bdc9b10', '{"title":"状态","dataIndex":"status"}', '46bd928a-0d5c-4e9c-948e-bfcc408e6e60', '0040');
INSERT INTO data_user.t_data_table_item (id, json_data, pid, sort_code) VALUES ('d6543bd5-b74e-4008-9baa-46570ac52e0c', '{"title":"描述","dataIndex":"description"}', '7b4203a9-02a5-441b-bd4c-128bc72046d4', '0040');
INSERT INTO data_user.t_data_table_item (id, json_data, pid, sort_code) VALUES ('dbaf9246-9b7e-4ba0-8940-ef1c3e98d6fe', '{"title":"目录代号","dataIndex":"catalogValue"}', 'e801febb-dadd-42be-836c-bfc699db0c04', '0020');
INSERT INTO data_user.t_data_table_item (id, json_data, pid, sort_code) VALUES ('e32586ae-4e6b-42ef-bdf7-5c04a17c7b9a', '{"title":"路径","dataIndex":"path"}', '7b4203a9-02a5-441b-bd4c-128bc72046d4', '0030');
INSERT INTO data_user.t_data_table_item (id, json_data, pid, sort_code) VALUES ('e946bfa3-e300-4331-bc74-5a5999edcc7c', '{"title":"描述","dataIndex":"description"}', '8c8be183-8b47-42c9-a65c-844d099073bf', '0020');
INSERT INTO data_user.t_data_table_item (id, json_data, pid, sort_code) VALUES ('f005182b-c420-46cc-8926-45c485cafa94', '{"title":"服务名称","dataIndex":"name"}', '715294ef-2877-486d-b227-1226bdd442e5', '0010');
INSERT INTO data_user.t_data_table_item (id, json_data, pid, sort_code) VALUES ('f20bb34d-6f7d-45fa-b926-f4db55b122df', '{"title":"接口路径","dataIndex":"apiPath"}', '59d64df5-fc3c-438c-aa3b-07e833788df5', '0050');
INSERT INTO data_user.t_data_table_item (id, json_data, pid, sort_code) VALUES ('ffe1a756-d679-46d3-af11-c7a560853311', '{"title":"键","dataIndex":"dictionaryName"}', '837df934-7fbc-4449-8fd3-f08da07a42b1', '0010');