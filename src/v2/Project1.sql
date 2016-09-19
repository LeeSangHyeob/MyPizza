create table MEMBER (
userid VARCHAR2(12) PRIMARY KEY ,
pwd VARCHAR2(12),
name VARCHAR2(10),
birth DATE ,
regdate DATE DEFAULT sysdate
);

create table dough (
dname varchar2(50) primary key,
damount number,
dprice number
)

create table source (
sname varchar2(50) primary key,
samount number,
sprice number
)

create table topping (
tname varchar2(50) primary key,
tamount number,
tprice number
)

create table sidemenu(
sidename varchar2(50) primary key,
sideprice number
)


create table ORDERLIST (
olistno NUMBER PRIMARY KEY ,
dname varchar2(50) references dough (dname),
sname VARCHAR2(50) references source (sname),
tname1 varchar2(50) references topping (tname),
tname2 varchar2(50) references topping (tname),
tname3 varchar2(50) references topping (tname),
tname4 varchar2(50) references topping (tname),
sidename1 varchar2(50) references sidemenu (sidename),
sidename2 varchar2(50) references sidemenu (sidename),
sidename3 varchar2(50) references sidemenu (sidename)
);

create table orders (
orderno number PRIMARY KEY ,
userid varchar2(12) references member (userid),
olistno number references orderlist (olistno)
);

insert into MEMBER (userid, pwd) values ('admin', 'admin');
