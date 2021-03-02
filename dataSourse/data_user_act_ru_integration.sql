create table act_ru_integration
(
    ID_                  varchar(64)  not null
        primary key,
    EXECUTION_ID_        varchar(64)  null,
    PROCESS_INSTANCE_ID_ varchar(64)  null,
    PROC_DEF_ID_         varchar(64)  null,
    FLOW_NODE_ID_        varchar(64)  null,
    CREATED_DATE_        timestamp(3) null,
    constraint ACT_FK_INT_EXECUTION
        foreign key (EXECUTION_ID_) references act_ru_execution (ID_)
            on delete cascade,
    constraint ACT_FK_INT_PROC_DEF
        foreign key (PROC_DEF_ID_) references act_re_procdef (ID_),
    constraint ACT_FK_INT_PROC_INST
        foreign key (PROCESS_INSTANCE_ID_) references act_ru_execution (ID_)
)
    collate = utf8_bin;

