create table review
(
	geulno int,
	movieidx int,
	geultext varchar2(100),
	userid varchar2(100),
	geuldate date 
)

--�⺻Ű
alter table review 
add constraint pk_geulno primary key(geulno)

--�ܷ�Ű
alter table review 
add constraint fk_review_movie foreign key(movieidx) references Movie(movieidx)

alter table review 
add constraint fk_review_member foreign key(userid) references member(userid)

select * from review


--sample data
insert into review values( (select nvl(max(geulno)+1,1) from review) , 1,  '�ȳ��ϼ���', '�浿1', '2022-05-11' );   
insert into review values( (select nvl(max(geulno)+1,1) from review) , 5,  '����ֳ׿�', '�浿2', '2022-05-09' );   
insert into review values( (select nvl(max(geulno)+1,1) from review) , 10, '�� �ٳ���',  '�浿3', '2022-05-27' );   
insert into review values( (select nvl(max(geulno)+1,1) from review) , 23, '���Ʊ���� ��', '�浿4', '2022-05-13' );  
insert into review values( (select nvl(max(geulno)+1,1) from review) , 28, '������ ��', '�浿5', '2022-05-12' );  

--data update
update review set userid = 'id1' where geulno = 1;
update review set userid = 'my123' where geulno = 2;
update review set userid = 'kimchi12' where geulno = 3;
update review set userid = 'bonglove' where geulno = 4;
update review set userid = 'gogo0325' where geulno = 5;
update review set movieidx = 13 where geulno = 4;
update review set movieidx = 19 where geulno = 5;


--��ȸ ��

create or replace view review_view
as
select 
geulno, title, geultext, nickname, geuldate 
from review r 
left outer join member m2 on r.userid = m2.userid
left outer join movie m on r.movieidx = m.movieidx

select * from review_view




