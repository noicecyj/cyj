create table t_menu_page
(
    id             varchar(32)  not null
        primary key,
    pid            varchar(32)  null,
    icon           varchar(255) null,
    sort_code      varchar(4)   null,
    component_name varchar(255) null,
    api_path       varchar(255) null,
    name           varchar(255) null,
    path           varchar(255) null,
    constraint FKll6niw2ej7c3oe25gkghookb3
        foreign key (pid) references t_menu_page (id)
);

INSERT INTO data_user.t_menu_page (id, pid, icon, sort_code, component_name, api_path, name, path) VALUES ('001', null, null, '0001', null, null, null, null);
INSERT INTO data_user.t_menu_page (id, pid, icon, sort_code, component_name, api_path, name, path) VALUES ('001-001', '001', 'edit', '0010', null, null, '开发管理', '/');
INSERT INTO data_user.t_menu_page (id, pid, icon, sort_code, component_name, api_path, name, path) VALUES ('001-001-001', '001-001', '', '0100', 'PageMenu', '/pageMenuApi', '菜单管理', '/pagemenu');
INSERT INTO data_user.t_menu_page (id, pid, icon, sort_code, component_name, api_path, name, path) VALUES ('001-001-002', '001-001', '', '0200', 'Dictionary', '/dictionaryApi', '数据字典', '/catalog');
INSERT INTO data_user.t_menu_page (id, pid, icon, sort_code, component_name, api_path, name, path) VALUES ('001-001-003', '001-001', '', '0300', 'EntityCreater', '/entityCreateApi', '实体生成器', '/entitycreater');
INSERT INTO data_user.t_menu_page (id, pid, icon, sort_code, component_name, api_path, name, path) VALUES ('001-001-004', '001-001', '', '0400', 'Log', '/logApi', '日志管理', '/log');
INSERT INTO data_user.t_menu_page (id, pid, icon, sort_code, component_name, api_path, name, path) VALUES ('001-001-005', '001-001', '', '0500', 'Sql', '/sqlApi', 'sql查询器', '/sql');
INSERT INTO data_user.t_menu_page (id, pid, icon, sort_code, component_name, api_path, name, path) VALUES ('001-001-006', '001-001', '', '0600', 'DataForm', '/pageMenuApi', '表单管理', '/dataform');
INSERT INTO data_user.t_menu_page (id, pid, icon, sort_code, component_name, api_path, name, path) VALUES ('001-001-007', '001-001', '', '0700', 'DataTable', '/pageMenuApi', '表格管理', '/dataTable');
INSERT INTO data_user.t_menu_page (id, pid, icon, sort_code, component_name, api_path, name, path) VALUES ('001-001-008', '001-001', '', '0800', 'AppService', '/appService', '服务管理', '/appService');
INSERT INTO data_user.t_menu_page (id, pid, icon, sort_code, component_name, api_path, name, path) VALUES ('001-002', '001', 'set', '0020', null, null, '系统管理', '/');
INSERT INTO data_user.t_menu_page (id, pid, icon, sort_code, component_name, api_path, name, path) VALUES ('001-002-001', '001-002', '', '0100', 'User', '/ssoApi', '用户管理', '/user');
INSERT INTO data_user.t_menu_page (id, pid, icon, sort_code, component_name, api_path, name, path) VALUES ('001-002-002', '001-002', '', '0200', 'Role', '/ssoApi', '角色管理', '/role');
INSERT INTO data_user.t_menu_page (id, pid, icon, sort_code, component_name, api_path, name, path) VALUES ('001-002-003', '001-002', '', '0300', 'Api', '/ssoApi', '接口管理', '/api');
INSERT INTO data_user.t_menu_page (id, pid, icon, sort_code, component_name, api_path, name, path) VALUES ('001-002-004', '001-002', '', '0400', 'PageFunction', '/ssoApi', '功能管理', '/pagefunction');