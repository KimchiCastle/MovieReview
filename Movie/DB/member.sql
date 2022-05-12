create table member
(
	userid 		 varchar2(100),
	nickname varchar2(100) not null,
	password varchar2(100) not null
)

drop table member

alter table member drop constraint pk_member_id;

alter table member
	add constraint pk_member_id primary key(userid);
	
alter table member add unique(nickname); 



insert into member values('id1','�������5��','123123');
insert into member values('my123','���ָ���7����','1q2w3e4r');
insert into member values('bonglove','����ȣ�����','bong123');
insert into member values('kimchi12','kimchi12','kimchi12');
insert into member values('gogo0325','��ȭ�л���','123123');

select * from member



update member set nickname = '�������5��' where userid = 'id1';