create table review
(
	geulno int,
	movieidx int,
	geultext varchar2(100),
	userid varchar2(100),
	geuldate date 
)

--기본키
alter table review 
add constraint pk_geulno primary key(geulno)

--외래키
alter table review 
add constraint fk_review_movie foreign key(movieidx) references Movie(movieidx)

alter table review 
add constraint fk_review_member foreign key(userid) references member(userid)

select * from review


--sample data
insert into review values( (select nvl(max(geulno)+1,1) from review) , 1,  '안녕하세요', '길동1', '2022-05-11' );   
insert into review values( (select nvl(max(geulno)+1,1) from review) , 5,  '재미있네요', '길동2', '2022-05-09' );   
insert into review values( (select nvl(max(geulno)+1,1) from review) , 10, '윽 핵노잼',  '길동3', '2022-05-27' );   
insert into review values( (select nvl(max(geulno)+1,1) from review) , 23, '돈아까워서 봄', '길동4', '2022-05-13' );  
insert into review values( (select nvl(max(geulno)+1,1) from review) , 28, '졸려서 잠', '길동5', '2022-05-12' );  

--data update
update review set userid = 'id1' where geulno = 1;
update review set userid = 'my123' where geulno = 2;
update review set userid = 'kimchi12' where geulno = 3;
update review set userid = 'bonglove' where geulno = 4;
update review set userid = 'gogo0325' where geulno = 5;
update review set movieidx = 13 where geulno = 4;
update review set movieidx = 19 where geulno = 5;


--조회 뷰

create or replace view review_view
as
select 
geulno, title, geultext, nickname, geuldate 
from review r 
left outer join member m2 on r.userid = m2.userid
left outer join movie m on r.movieidx = m.movieidx

select * from review_view




