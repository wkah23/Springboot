drop table myuser;
create table myuser (
    id varchar2(10),
    name varchar2(10)
);

-- ���� ������ ���� �߰��ϱ�
insert into myuser values ( 'test1', 'ȫ�浿1');
insert into myuser values ( 'test2', 'ȫ�浿2');
insert into myuser values ( 'test3', 'ȫ�浿3');
commit;


drop table simple_bbs;
create table simple_bbs (
    id number(4) primary key,
    writer varchar2(100),
    title varchar2(100),
    content varchar2(100)
);

drop sequence simple_bbs_seq;
create sequence simple_bbs_seq;

create table Transaction1 (
    consumerId varchar2(10),
    amount number
);

create table Transaction2 (
    consumerId varchar2(10),
    amount number
);

create table Transaction3 (
    consumerId varchar2(10),
    amount number
);
drop table user_list;
create table user_list (
    name varchar2(20) primary key,
    password varchar2(100),
    authority varchar(20),
    enabled number(1)
);

insert into user_list values ('user', '$2a$10$CYkRM6C0e0FPWJcuuS/fsuIColt8PaGRm8XOE7dB1Hbmf9tjEXj5', 'ROLE_USER', 1);
insert into user_list values ('admin', '$2a$10$CYkRM6C0e0FPWJcuuS/fsuIColt8PaGRm8XOE7dB1Hbmf9tjEXj5', 'ROLE_ADMIN', 1);
commit;
select * from user_list;

