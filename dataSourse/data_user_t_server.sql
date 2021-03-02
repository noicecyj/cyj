create table t_server
(
    id          varchar(36)  not null
        primary key,
    description varchar(255) null,
    server_name varchar(255) null,
    server_port varchar(255) null,
    sort_code   varchar(255) null
);

INSERT INTO data_user.t_server (id, description, server_name, server_port, sort_code) VALUES ('4028818373d196090173d19ace7e0000', '单点登录验证服务', 'cyj-sso', '8050', '0006');
INSERT INTO data_user.t_server (id, description, server_name, server_port, sort_code) VALUES ('40288183740c5ff801740c65089b0000', '工作流服务中心', 'cyj-workflow', '8060', '0007');
INSERT INTO data_user.t_server (id, description, server_name, server_port, sort_code) VALUES ('4028818473b9e81e0173ba255f370001', '数据字典服务', 'cyj-dictionary', '8030', '0004');
INSERT INTO data_user.t_server (id, description, server_name, server_port, sort_code) VALUES ('4028818673be99b40173bea1c3f10000', '服务注册中心', 'cyj-eureka', '8761', '0001');
INSERT INTO data_user.t_server (id, description, server_name, server_port, sort_code) VALUES ('4028818673bee4950173bf0774690000', '实体生成服务', 'cyj-entity-creater', '8020', '0003');
INSERT INTO data_user.t_server (id, description, server_name, server_port, sort_code) VALUES ('4028818673bee4950173bf09d9430001', '菜单服务', 'cyj-page-menu', '8010', '0002');
INSERT INTO data_user.t_server (id, description, server_name, server_port, sort_code) VALUES ('4028818673bee4950173bf0e10b10002', '日志服务', 'cyj-log', '8040', '0005');
INSERT INTO data_user.t_server (id, description, server_name, server_port, sort_code) VALUES ('40288186743ce19701743cfac0230000', 'sql查询服务', 'cyj-query', '8070', '0008');