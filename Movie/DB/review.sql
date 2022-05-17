create table review
(
	geulno int,
	movieidx int,
	geultext varchar2(100),
	userid varchar2(100),
	geuldate date 
)

update review set geultext='1', geuldate= SYSDATE where geulno=1

--�⺻Ű
alter table review 
add constraint pk_geulno primary key(geulno)

alter table review add constraint fk_userid foreign key(userid) references member(userid)	
alter table review add constraint fk_movieidx foreign key(movieidx) references movie(movieidx)	

alter table review drop constraint fk_movieidx 

update review set geultext="�����λ���ȭ", geuldate=



select * from review



drop table review 
--sample data
insert into review values( (select nvl(max(geulno)+1,1) from review) , 1,  '�ȳ��ϼ���', 'id1', '2022-05-11' );   
insert into review values( (select nvl(max(geulno)+1,1) from review) , 5,  '����ֳ׿�', 'my123', '2022-05-09' );   
insert into review values( (select nvl(max(geulno)+1,1) from review) , 10, '�� �ٳ���',  'bonglove', '2022-05-27' );   
insert into review values( (select nvl(max(geulno)+1,1) from review) , 20, '���Ʊ���� ��', 'kimchi12', '2022-05-13' );  
insert into review values( (select nvl(max(geulno)+1,1) from review) , 21, '������ ��', 'gogo0325', '2022-05-12' );   

insert into review values( (select nvl(max(geulno)+1,1) from review) , 2,  '�����߾��^^', 'id1', '2022-05-11' );   
insert into review values( (select nvl(max(geulno)+1,1) from review) , 3,  '����..', 'my123', '2022-05-09' );   
insert into review values( (select nvl(max(geulno)+1,1) from review) , 4, '5�������� 6�� �帳�ϴ�',  'bonglove', '2022-05-27' );   
insert into review values( (select nvl(max(geulno)+1,1) from review) , 6, '��մµ� ������..?', 'kimchi12', '2022-05-13' );  
insert into review values( (select nvl(max(geulno)+1,1) from review) , 7,  '��̾��׿�', 'gogo0325', '2022-05-12' );  
insert into review values( (select nvl(max(geulno)+1,1) from review) , 1,  '�Ƶ��̶� ���� �þ��', 'id1', '2022-05-11' );   
insert into review values( (select nvl(max(geulno)+1,1) from review) , 8,  '����� ����..', 'my123', '2022-05-09' );   
insert into review values( (select nvl(max(geulno)+1,1) from review) , 9, '���ٰ� ���ܳ���;',  'bonglove', '2022-05-27' );   
insert into review values( (select nvl(max(geulno)+1,1) from review) , 11, '3ȸ���Դϴ�', 'kimchi12', '2022-05-13' );  
insert into review values( (select nvl(max(geulno)+1,1) from review) , 12, '�����ҵƳ׿�', 'gogo0325', '2022-05-12' );   
insert into review values( (select nvl(max(geulno)+1,1) from review) , 13,  '��������ġ�͸¹��ȴ�.', 'id1', '2022-05-11' );   
insert into review values( (select nvl(max(geulno)+1,1) from review) , 15,  '�þ����¾������־���', 'my123', '2022-05-09' );   
insert into review values( (select nvl(max(geulno)+1,1) from review) , 16, '������������',  'bonglove', '2022-05-27' );   
insert into review values( (select nvl(max(geulno)+1,1) from review) , 17, '��¥��ѿ�ȭ��', 'kimchi12', '2022-05-13' );  
insert into review values( (select nvl(max(geulno)+1,1) from review) , 18, '�ѹ������ȵɿ�ȭ', 'gogo0325', '2022-05-12' );   
insert into review values( (select nvl(max(geulno)+1,1) from review) , 19, '�ٸ���ȭ��Ŀ�Ƕ���̿�ȭ��top', 'gogo0325', '2022-05-12' );   
geulno int,
	movieidx int,
	geultext varchar2(100),
	userid varchar2(100),
	geuldate date 

selcet * from movie

