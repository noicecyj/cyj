create table t_app_service
(
    id        varchar(36)  not null
        primary key,
    app_api   varchar(255) null,
    sort_code varchar(255) null,
    app_port  varchar(255) null,
    name      varchar(255) null
);

INSERT INTO data_user.t_app_service (id, app_api, sort_code, app_port, name) VALUES ('64f1a526-cdb4-470d-9360-0c8ff521a8e4', 'pageMenuApi', '0040', '8010', 'cyj-page-menu');
INSERT INTO data_user.t_app_service (id, app_api, sort_code, app_port, name) VALUES ('9137cbd4-e883-4985-819e-ba8f5899b735', 'workflowApi', '0070', '8060', 'cyj-workflow');
INSERT INTO data_user.t_app_service (id, app_api, sort_code, app_port, name) VALUES ('aeeea8ea-4d6b-4e96-a2ae-f7b8375a8041', 'dictionaryApi', '0030', '8030', 'cyj-dictionary');
INSERT INTO data_user.t_app_service (id, app_api, sort_code, app_port, name) VALUES ('b6f85556-5ee8-4eab-a729-ba3f1d3d137b', 'entityCreateApi', '0020', '8020', 'cyj-entity-creater');
INSERT INTO data_user.t_app_service (id, app_api, sort_code, app_port, name) VALUES ('bcaec365-3132-4ad2-ba0f-dbdec615bc3a', 'logApi', '0080', '8040', 'cyj-log');
INSERT INTO data_user.t_app_service (id, app_api, sort_code, app_port, name) VALUES ('d9e09623-9dd1-47f0-a48e-2de4425a7c4a', 'ssoApi', '0060', '8050', 'cyj-sso');
INSERT INTO data_user.t_app_service (id, app_api, sort_code, app_port, name) VALUES ('e00f7009-86ea-43c0-9106-751b7a4bf476', '无', '0010', '8761', 'cyj-eureka');
INSERT INTO data_user.t_app_service (id, app_api, sort_code, app_port, name) VALUES ('ff3b4fc9-8211-4b6a-8aa2-1b0f64ddeef0', 'sqlApi', '0050', '8070', 'cyj-query');