create table act_re_deployment
(
    ID_                      varchar(64)             not null
        primary key,
    NAME_                    varchar(255)            null,
    CATEGORY_                varchar(255)            null,
    KEY_                     varchar(255)            null,
    TENANT_ID_               varchar(255) default '' null,
    DEPLOY_TIME_             timestamp(3)            null,
    ENGINE_VERSION_          varchar(255)            null,
    VERSION_                 int          default 1  null,
    PROJECT_RELEASE_VERSION_ varchar(255)            null
)
    collate = utf8_bin;

INSERT INTO data_user.act_re_deployment (ID_, NAME_, CATEGORY_, KEY_, TENANT_ID_, DEPLOY_TIME_, ENGINE_VERSION_, VERSION_, PROJECT_RELEASE_VERSION_) VALUES ('6da92ad9-fd9c-11ea-b47b-40b076ddbf46', 'SpringAutoDeployment', null, null, '', '2020-09-23 12:58:07.956', null, 1, null);
INSERT INTO data_user.act_re_deployment (ID_, NAME_, CATEGORY_, KEY_, TENANT_ID_, DEPLOY_TIME_, ENGINE_VERSION_, VERSION_, PROJECT_RELEASE_VERSION_) VALUES ('a1bf8fcc-fd9d-11ea-859a-40b076ddbf46', 'SpringAutoDeployment', null, null, '', '2020-09-23 13:06:45.334', null, 2, null);