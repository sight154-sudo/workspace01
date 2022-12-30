-- 删除表中重复的数据，只保存id最小的数据

delete
from people
where peopleName in (select peopleName from people group by peopleName having count(peopleName) > 1)
  and peopleId not in (select min(peopleId) from people group by peopleName having count(peopleName) > 1)
-- 查询重复数据
select count(*), id
from ld_api
group by id
having count(*) > 1;

-- 删除重复数据
delete
from ld_api
where id = (select id from ld_api group by id having count(*) > 1)
 and api_name != (select api_name from ld_api group by id having count(*) >1 limit 1 );



-- rand_name函数
CREATE DEFINER=`root`@`localhost` FUNCTION `rand_name`(num int) RETURNS varchar(32) CHARSET utf8
begin
	declare origin_str char(26) default 'abcdefghijklmnopqrstuvwxyz';
  declare rtn_str char(255) default '';
	declare i int default 0;
	while i < num do
		set rtn_str = concat(rtn_str,substring(origin_str, floor(1+rand()*26),1));
		set i = i+1;
end while;
return rtn_str;
end


-- add_api_Data
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_api`()
begin
	declare i int default 0;
	declare flag int default 1;
	declare method varchar(10);
	while i < 100000 do
		set i = i+1;
		set flag = floor(rand()*100)%2;
    if flag = 0 then
        set method = 'get';
    else
      set method = 'post';
    end if;
    insert into ld_api(`id`,`api_uuid`,`api_name`,`api_method`,`api_createtime`,`api_modifytime`,`api_deleteflag`)
    values(i,replace(uuid(),'-',''),rand_name(8),method,now(),now(),flag);
    end while;
end



-- 去除重复数据，保留日期最近的数据
select
    a.id,a.user_id,a.problems,a.last_updated_date, b.*
from t_test a
         left join t_test b on a.user_id = b.user_id  and a.last_updated_date < b.last_updated_date
where b.id is null;

explain
select
    a.*
from t_test a
where a.last_updated_date in
      (select max(last_updated_date) from t_test b where a.user_id = b.user_id);


select a.*
from t_test a
where  not exists(select 1 from t_test b where a.user_id = b.user_id and b.last_updated_date > a.last_updated_date);