alter table doctors add inactive tinyint;
update doctors set inactive=0;
alter table doctors modify inactive tinyint not null;