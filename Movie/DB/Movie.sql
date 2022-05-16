create table movie
(
movieidx int,
title varchar2(100),
cateno int,

constraint movie_pk_movieidx primary key(movieidx)
)

alter table movie
	add constraint fk_cateno foreign key(cateno) references category(cateno);



drop table movie


select * from movie

insert into movie values(1,'�о�',1);
insert into movie values(2,'����',2);
insert into movie values(3,'���',3);
insert into movie values(4,'��',4);
insert into movie values(5,'�Ѱ���',5);
insert into movie values(6,'�����',5);
insert into movie values(7,'�ϻ�',1);
insert into movie values(8,'����',4);
insert into movie values(16,'�ظ�����',2);


select
movieidx, title, catename
from movie m left outer join category c on m.cateno = c.cateno




insert into movie values(9,'û�����',6);
insert into movie values(10,'��ٿ�Ÿ��',1);
insert into movie values(11,'��Ʈ��',1);
insert into movie values(12,'Ÿ¥',3);
insert into movie values(13,'�λ����Ƹ��ٿ�',1);
insert into movie values(14,'���̽��丮3',1);
insert into movie values(15,'ĳ�����������',2);
insert into movie values(17,'����',1);
insert into movie values(18,'����',1);
insert into movie values(19,'Ÿ��Ÿ��',1);
insert into movie values(20,'��',6);
insert into movie values(21,'�μ���',3);
insert into movie values(22,'�����ħ��',3);




