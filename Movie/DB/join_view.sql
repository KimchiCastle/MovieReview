
--review view
create or replace view review_view
as
select 
geulno, title, geultext, nickname, geuldate 
from review r 
left outer join member m2 on r.userid = m2.userid
left outer join movie m on r.movieidx = m.movieidx


select * from review_view 



--category view
create or replace view category_view
as
select 
movieidx, m.cateno, catename, title
from MOVIE m left outer join category c on m.cateno = c.cateno

select movieidx, title from category_view where cateno = 1;


--¿¬½À
select nickname
from review_view where geulno = 1;

 select * from review_view
 
 
 