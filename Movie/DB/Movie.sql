create table movie
(
movieidx int,
title varchar2(100),
cateno int,

constraint movie_pk_movieidx primary key(movieidx)
)

alter table movie
	add constraint fk_cateno foreign key(cateno) references category(cateno);

alter table movie add cateno int  

drop table movie


select * from movie

insert into movie values(1,'밀양',1);
insert into movie values(2,'괴물',2);
insert into movie values(3,'써니',3);
insert into movie values(4,'콜',4);
insert into movie values(5,'한공주',5);
insert into movie values(6,'어벤져스',5);
insert into movie values(7,'암살',1);
insert into movie values(8,'협상',4);
insert into movie values(16,'해리포터',2);


select
movieidx, title, catename
from movie m left outer join category c on m.cateno = c.cateno




insert into movie values(9,'청년경찰',6);
insert into movie values(10,'어바웃타임',1);
insert into movie values(11,'노트북',1);
insert into movie values(12,'타짜',3);
insert into movie values(13,'인생은아름다워',1);
insert into movie values(14,'토이스토리3',1);
insert into movie values(15,'캐리비안의해적',2);
insert into movie values(17,'코코',1);
insert into movie values(18,'레옹',1);
insert into movie values(19,'타이타닉',1);
insert into movie values(20,'업',6);
insert into movie values(21,'인셉션',3);
insert into movie values(22,'양들의침묵',3);


update movie set cateno=1 where movieidx=1;
update movie set cateno=2 where movieidx=2;
update movie set cateno=3 where movieidx=3;
update movie set cateno=4 where movieidx=4;
update movie set cateno=5 where movieidx=5;
update movie set cateno=5 where movieidx=6;
update movie set cateno=1 where movieidx=7;
update movie set cateno=4 where movieidx=8;
update movie set cateno=6 where movieidx=9;
update movie set cateno=1 where movieidx=10;
update movie set cateno=1 where movieidx=11;
update movie set cateno=3 where movieidx=12;
update movie set cateno=1 where movieidx=13;
update movie set cateno=1 where movieidx=14;
update movie set cateno=2 where movieidx=15;
update movie set cateno=2 where movieidx=16;
update movie set cateno=1 where movieidx=17;
update movie set cateno=1 where movieidx=18;
update movie set cateno=1 where movieidx=19;
update movie set cateno=6 where movieidx=20;
update movie set cateno=3 where movieidx=21;
update movie set cateno=3 where movieidx=22;

