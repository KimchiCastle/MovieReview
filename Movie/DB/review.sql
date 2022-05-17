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

insert into review values( (select nvl(max(geulno)+1,1) from review) , 2,  '가입했어요^^', 'id1', '2022-05-11' );   
insert into review values( (select nvl(max(geulno)+1,1) from review) , 3,  '존잼..', 'my123', '2022-05-09' );   
insert into review values( (select nvl(max(geulno)+1,1) from review) , 4, '5점만점에 6점 드립니다',  'bonglove', '2022-05-27' );   
insert into review values( (select nvl(max(geulno)+1,1) from review) , 6, '재밌는데 졸았음..?', 'kimchi12', '2022-05-13' );  
insert into review values( (select nvl(max(geulno)+1,1) from review) , 7,  '재미었네요', 'gogo0325', '2022-05-12' );  
insert into review values( (select nvl(max(geulno)+1,1) from review) , 1,  '아들이랑 같이 봤어요', 'id1', '2022-05-11' );   
insert into review values( (select nvl(max(geulno)+1,1) from review) , 8,  '배우진 무엇..', 'my123', '2022-05-09' );   
insert into review values( (select nvl(max(geulno)+1,1) from review) , 9, '보다가 팝콘날림;',  'bonglove', '2022-05-27' );   
insert into review values( (select nvl(max(geulno)+1,1) from review) , 11, '3회차입니다', 'kimchi12', '2022-05-13' );  
insert into review values( (select nvl(max(geulno)+1,1) from review) , 12, '감독팬됐네요', 'gogo0325', '2022-05-12' );   
insert into review values( (select nvl(max(geulno)+1,1) from review) , 13,  '세상의이치와맞물렸다.', 'id1', '2022-05-11' );   
insert into review values( (select nvl(max(geulno)+1,1) from review) , 15,  '늘어지는씬이좀있었음', 'my123', '2022-05-09' );   
insert into review values( (select nvl(max(geulno)+1,1) from review) , 16, '와음향조명무엇',  'bonglove', '2022-05-27' );   
insert into review values( (select nvl(max(geulno)+1,1) from review) , 17, '진짜비싼영화다', 'kimchi12', '2022-05-13' );  
insert into review values( (select nvl(max(geulno)+1,1) from review) , 18, '한번봐선안될영화', 'gogo0325', '2022-05-12' );   
insert into review values( (select nvl(max(geulno)+1,1) from review) , 19, '다른영화가커피라면이영화는top', 'gogo0325', '2022-05-12' );   
geulno int,
	movieidx int,
	geultext varchar2(100),
	userid varchar2(100),
	geuldate date 

selcet * from movie

