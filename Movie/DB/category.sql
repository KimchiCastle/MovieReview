create table category
(
	cateno int,
	catename varchar2(100) not null
)
sele

alter table category
	add constraint pk_cateno primary key(cateno);

select * from CATEGORY



insert into category values(1, '���');
insert into category values(2, '��Ÿ��');
insert into category values(3, '����/�׼�');
insert into category values(4, '������');
insert into category values(5, 'SF');
insert into category values(6, '�ڹ�');


drop table category
