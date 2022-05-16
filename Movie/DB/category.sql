create table category
(
	cateno int,
	catename varchar2(100) not null
)
sele

alter table category
	add constraint pk_cateno primary key(cateno);

select * from CATEGORY



insert into category values(1, '드라마');
insert into category values(2, '판타지');
insert into category values(3, '범죄/액션');
insert into category values(4, '스릴러');
insert into category values(5, 'SF');
insert into category values(6, '코믹');


drop table category
