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
);

create table sidemenu(
sidename varchar2(50) primary key,
sideamount number,
sideprice number
);

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
olistno number references orderlist (olistno),
totalprice number,
orderdate date default sysdate
);

insert into MEMBER (userid, pwd) values ('admin', 'admin');

insert into dough values ('오리지널 M', 642, 14000);
insert into dough values ('오리지널 L', 1104, 19000);
insert into dough values ('나폴리 M', 516, 14000);
insert into dough values ('나폴리 L', 904, 19000);
insert into dough values ('씬 M', 420, 14000);
insert into dough values ('씬 L', 772, 19000);
insert into dough values ('더블치즈 M', 754, 17000);
insert into dough values ('더블치즈 L', 1350, 24000);
insert into dough values ('히든엣지 M', 695, 17000);
insert into dough values ('히든엣지 L', 1243, 24000);

insert into topping values ('양파 M', 40, 500);
insert into topping values ('양파 L', 60, 1000);
insert into topping values ('양송이 버섯 M', 40, 500);
insert into topping values ('양송이 버섯 L', 60, 1000);
insert into topping values ('피망 M', 40, 500);
insert into topping values ('피망 L', 60, 1000);
insert into topping values ('올리브 M', 40, 500);
insert into topping values ('올리브 L', 60, 1000);
insert into topping values ('파인애플 M', 40, 500);
insert into topping values ('파인애플 L', 80, 1000);
insert into topping values ('페퍼로니 M', 28, 500);
insert into topping values ('페퍼로니 L', 56, 1000);
insert into topping values ('베이컨 M', 24, 1000);
insert into topping values ('베이컨 L', 48, 2000);
insert into topping values ('감자 M', 80, 1000);
insert into topping values ('감자 L', 160, 2000);
insert into topping values ('새우 M', 62, 4000);
insert into topping values ('새우 L', 124, 6000);

insert into sidemenu values ('콘샐러드', 100, 2400);
insert into sidemenu values ('코울슬로', 100, 2400);
insert into sidemenu values ('버팔로윙', 850, 4500);
insert into sidemenu values ('스프라이트 500ML', 0, 1200);
insert into sidemenu values ('스프라이트 1.5L', 0, 1900);
insert into sidemenu values ('코카콜라 500ML', 0, 1100);
insert into sidemenu values ('코카콜라 1.5L', 0, 1600);
insert into sidemenu values ('피클', 120, 600);
insert into sidemenu values ('감자튀김', 208, 3000);
insert into sidemenu values ('스테이크 샐러드', 445, 6900);
insert into sidemenu values ('크랜베리 샐러드', 445, 6900);

insert into source (sname) values ('토마토 소스');
insert into source (sname) values ('크림갈릭 소스');
insert into source (sname) values ('바베큐 소스');
insert into source (sname) values ('버팔로 소스');
insert into source (sname) values ('랜치 소스');
insert into source (sname) values ('데리야끼 소스');

create sequence olistno;
create sequence orderno;

drop table orders;
drop table orderlist;
drop sequence olistno;
drop sequence orderno;
