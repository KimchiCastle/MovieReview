create table review
(
	geulno int,
	movieidx int,
	geultext varchar2(100),
	userid varchar2(100),
	geuldate date 
)

update review set geultext='1', geuldate= SYSDATE where geulno=1

--기본키
alter table review 
add constraint pk_geulno primary key(geulno)

alter table review add constraint fk_userid foreign key(userid) references member(userid)	
alter table review add constraint fk_movieidx foreign key(movieidx) references movie(movieidx)	

alter table review drop constraint fk_movieidx 

update review set geultext="진심인생영화", geuldate=



select * from review



drop table review 
--sample data
insert into review values( (select nvl(max(geulno)+1,1) from review) , 1,  '안녕하세요', 'id1', '2022-05-11' );   
insert into review values( (select nvl(max(geulno)+1,1) from review) , 5,  '재미있네요', 'my123', '2022-05-09' );   
insert into review values( (select nvl(max(geulno)+1,1) from review) , 10, '윽 핵노잼',  'bonglove', '2022-05-27' );   
insert into review values( (select nvl(max(geulno)+1,1) from review) , 20, '돈아까워서 봄', 'kimchi12', '2022-05-13' );  
insert into review values( (select nvl(max(geulno)+1,1) from review) , 21, '졸려서 잠', 'gogo0325', '2022-05-12' );   


