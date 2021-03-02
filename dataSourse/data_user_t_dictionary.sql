create table t_dictionary
(
    id               varchar(36)  not null
        primary key,
    dictionary_name  varchar(255) null,
    dictionary_value varchar(255) null,
    pid              varchar(36)  null,
    sort_code        varchar(255) null
);

INSERT INTO data_user.t_dictionary (id, dictionary_name, dictionary_value, pid, sort_code) VALUES ('032a3963-a556-4bf3-9c63-440bcd13eb71', '表格', '表格', '1a5f1907-caf5-47c1-b37d-edeeed864ec2', '0020');
INSERT INTO data_user.t_dictionary (id, dictionary_name, dictionary_value, pid, sort_code) VALUES ('055baf88-f706-4046-b6a5-d9e2ebb29079', 'model', 'model', '08f8ed34-1028-4580-b542-8ca45ba3d61c', '0010');
INSERT INTO data_user.t_dictionary (id, dictionary_name, dictionary_value, pid, sort_code) VALUES ('05cdaab2-1c97-4bc4-bfbb-af804333', '已生成', '1', 'afa82144-6b85-44b8-b303-c2c63b26', '0010');
INSERT INTO data_user.t_dictionary (id, dictionary_name, dictionary_value, pid, sort_code) VALUES ('0f360130-006d-45ce-bcc4-a20ab794156a', 'dao', 'dao', '16a7313b-a159-4766-bdf8-597d4af691a4', '0020');
INSERT INTO data_user.t_dictionary (id, dictionary_name, dictionary_value, pid, sort_code) VALUES ('2c0e8a99-aa89-41a6-99aa-60410ea0', '更新', '更新', 'a4f846e4-47f7-4f08-9f17-0941e2e0', '0020');
INSERT INTO data_user.t_dictionary (id, dictionary_name, dictionary_value, pid, sort_code) VALUES ('2f67ffa4-b584-48d0-b320-51e426d0', '查询', '查询', 'a4f846e4-47f7-4f08-9f17-0941e2e0', '0010');
INSERT INTO data_user.t_dictionary (id, dictionary_name, dictionary_value, pid, sort_code) VALUES ('4028818373f751af0173f75bbe690001', 'routePath', 'C:\\Users\\95263\\IdeaProjects\\noiceqd\\src', '4028818373f751af0173f75b3ec80000', '0010');
INSERT INTO data_user.t_dictionary (id, dictionary_name, dictionary_value, pid, sort_code) VALUES ('4028818374019b60017401a57c2d0000', 'componentPath', 'C:\\Users\\95263\\IdeaProjects\\noiceqd\\src\\pages', '4028818373f751af0173f75b3ec80000', '0020');
INSERT INTO data_user.t_dictionary (id, dictionary_name, dictionary_value, pid, sort_code) VALUES ('402881857353170801735328a62b0004', 'PO', 'PO', '402881857353170801735321e3f30000', '0010');
INSERT INTO data_user.t_dictionary (id, dictionary_name, dictionary_value, pid, sort_code) VALUES ('402881857353170801735328d3cf0005', 'VO', 'VO', '402881857353170801735321e3f30000', '0020');
INSERT INTO data_user.t_dictionary (id, dictionary_name, dictionary_value, pid, sort_code) VALUES ('40288185735317080173532903390006', 'BO', 'BO', '402881857353170801735321e3f30000', '0030');
INSERT INTO data_user.t_dictionary (id, dictionary_name, dictionary_value, pid, sort_code) VALUES ('4028818573531708017353292d0e0007', 'DTO', 'DTO', '402881857353170801735321e3f30000', '0040');
INSERT INTO data_user.t_dictionary (id, dictionary_name, dictionary_value, pid, sort_code) VALUES ('402881857353170801735329a2920008', '是', 'Y', '4028818573531708017353228dad0001', '0010');
INSERT INTO data_user.t_dictionary (id, dictionary_name, dictionary_value, pid, sort_code) VALUES ('402881857353170801735329d5c60009', '否', 'N', '4028818573531708017353228dad0001', '0020');
INSERT INTO data_user.t_dictionary (id, dictionary_name, dictionary_value, pid, sort_code) VALUES ('40288185735317080173532a2b1a000a', 'Constructor', 'Constructor', '4028818573531708017353277b6c0002', '0010');
INSERT INTO data_user.t_dictionary (id, dictionary_name, dictionary_value, pid, sort_code) VALUES ('40288185735317080173532a521c000b', 'Getter and Setter', 'Getter and Setter', '4028818573531708017353277b6c0002', '0020');
INSERT INTO data_user.t_dictionary (id, dictionary_name, dictionary_value, pid, sort_code) VALUES ('40288185735317080173532a7da7000c', 'equals and hashCode', 'equals and hashCode', '4028818573531708017353277b6c0002', '0030');
INSERT INTO data_user.t_dictionary (id, dictionary_name, dictionary_value, pid, sort_code) VALUES ('40288185735317080173532a9e76000d', 'toString', 'toString', '4028818573531708017353277b6c0002', '0040');
INSERT INTO data_user.t_dictionary (id, dictionary_name, dictionary_value, pid, sort_code) VALUES ('40288185735317080173532acb35000e', 'String', 'String', '4028818573531708017353282db10003', '0010');
INSERT INTO data_user.t_dictionary (id, dictionary_name, dictionary_value, pid, sort_code) VALUES ('40288185735317080173532aea9e000f', 'Integer', 'Integer', '4028818573531708017353282db10003', '0020');
INSERT INTO data_user.t_dictionary (id, dictionary_name, dictionary_value, pid, sort_code) VALUES ('40288185735317080173532b0e9b0010', 'Date', 'Date', '4028818573531708017353282db10003', '0030');
INSERT INTO data_user.t_dictionary (id, dictionary_name, dictionary_value, pid, sort_code) VALUES ('40288185735317080173532b45080011', 'Short', 'Short', '4028818573531708017353282db10003', '0040');
INSERT INTO data_user.t_dictionary (id, dictionary_name, dictionary_value, pid, sort_code) VALUES ('40288185735317080173532bbcb20012', 'Byte', 'Byte', '4028818573531708017353282db10003', '0050');
INSERT INTO data_user.t_dictionary (id, dictionary_name, dictionary_value, pid, sort_code) VALUES ('40288185735317080173532be87c0013', 'Long', 'Long', '4028818573531708017353282db10003', '0060');
INSERT INTO data_user.t_dictionary (id, dictionary_name, dictionary_value, pid, sort_code) VALUES ('40288185735317080173532c0b3d0014', 'Double', 'Double', '4028818573531708017353282db10003', '0070');
INSERT INTO data_user.t_dictionary (id, dictionary_name, dictionary_value, pid, sort_code) VALUES ('40288185735317080173532c2a310015', 'Character', 'Character', '4028818573531708017353282db10003', '0080');
INSERT INTO data_user.t_dictionary (id, dictionary_name, dictionary_value, pid, sort_code) VALUES ('40288185735317080173532c486c0016', 'Boolean', 'Boolean', '4028818573531708017353282db10003', '0090');
INSERT INTO data_user.t_dictionary (id, dictionary_name, dictionary_value, pid, sort_code) VALUES ('40288185735317080173532c793c0017', 'Timestamp', 'Timestamp', '4028818573531708017353282db10003', '0100');
INSERT INTO data_user.t_dictionary (id, dictionary_name, dictionary_value, pid, sort_code) VALUES ('4366586f-4f8f-4950-ad18-68a6a13c', '删除', '删除', 'a4f846e4-47f7-4f08-9f17-0941e2e0', '0040');
INSERT INTO data_user.t_dictionary (id, dictionary_name, dictionary_value, pid, sort_code) VALUES ('5877924e-61e5-4320-8efb-6df2a87fefd4', 'entity', 'entity', '16a7313b-a159-4766-bdf8-597d4af691a4', '0010');
INSERT INTO data_user.t_dictionary (id, dictionary_name, dictionary_value, pid, sort_code) VALUES ('5d8ba729-883f-43d9-8da1-dc6161c317c2', 'service', 'service', '16a7313b-a159-4766-bdf8-597d4af691a4', '0030');
INSERT INTO data_user.t_dictionary (id, dictionary_name, dictionary_value, pid, sort_code) VALUES ('5febc7ab-fe42-44ac-a656-d4c6050af36e', 'service', 'service', '08f8ed34-1028-4580-b542-8ca45ba3d61c', '0020');
INSERT INTO data_user.t_dictionary (id, dictionary_name, dictionary_value, pid, sort_code) VALUES ('72803cdd-d614-40c8-8385-a1a15490', '新增', '新增', 'a4f846e4-47f7-4f08-9f17-0941e2e0', '0030');
INSERT INTO data_user.t_dictionary (id, dictionary_name, dictionary_value, pid, sort_code) VALUES ('7e894342-069d-4ec5-8cfc-e59edbb7', '未生成', '0', 'afa82144-6b85-44b8-b303-c2c63b26', '0020');
INSERT INTO data_user.t_dictionary (id, dictionary_name, dictionary_value, pid, sort_code) VALUES ('81d15079-e553-41f6-8bb0-49c40b821762', 'servicePath', 'C:\\Users\\95263\\IdeaProjects\\cyj\\', '4028818373f751af0173f75b3ec80000', '0030');
INSERT INTO data_user.t_dictionary (id, dictionary_name, dictionary_value, pid, sort_code) VALUES ('83a5c308-acca-4e65-8288-0256beec12db', 'controller', 'controller', '16a7313b-a159-4766-bdf8-597d4af691a4', '0040');
INSERT INTO data_user.t_dictionary (id, dictionary_name, dictionary_value, pid, sort_code) VALUES ('dfd51310-0148-431c-9ffe-1623c0579a19', 'index', 'index', '08f8ed34-1028-4580-b542-8ca45ba3d61c', '0030');
INSERT INTO data_user.t_dictionary (id, dictionary_name, dictionary_value, pid, sort_code) VALUES ('f0c150aa-29d2-4399-964c-0d406e59f834', '表单', '表单', '1a5f1907-caf5-47c1-b37d-edeeed864ec2', '0010');