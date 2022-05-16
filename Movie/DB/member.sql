create table member
(
	userid 		 varchar2(100),
	nickname varchar2(100) not null,
	password varchar2(100) not null
)

drop table member


alter table member
	add constraint pk_member_id primary key(userid);
	
alter table member add unique(nickname); 

select * from member

insert into member values('id1','박평식의5점','123123');
insert into member values('my123','우주명작7광구','1q2w3e4r');
insert into member values('bonglove','봉준호사랑해','bong123');
insert into member values('kimchi12','kimchi12','kimchi12');
insert into member values('gogo0325','영화학살자','123123');

alter table member drop constraint pk_member_id;


update member set nickname = '박평식의5점' where userid = 'id1';