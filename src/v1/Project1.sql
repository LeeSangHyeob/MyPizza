create table MEMBER (
userid VARCHAR2(12) PRIMARY KEY ,
pwd VARCHAR2(12),
name VARCHAR2(10) ,
birth DATE ,
regdate DATE DEFAULT sysdate
);

create table ORDERLIST (
olistno NUMBER PRIMARY KEY ,
sname VARCHAR2(20),
dname varchar2(20),
tname1 varchar2(20),
tname2 varchar2(20),
tname3 varchar2(20),
tname4 varchar2(20),
tname5 varchar2(20),
tname6 varchar2(20),
sidename1 varchar2(20),
sidename2 varchar2(20),
sidename3 varchar2(20)
);

insert into MEMBER (userid, pwd) values ('admin', 'admin');