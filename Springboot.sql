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