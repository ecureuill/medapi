alter table patients add inactive tinyint;
update patients set inactive=0;
alter table patients modify inactive tinyint not null;