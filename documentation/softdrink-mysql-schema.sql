CREATE SCHEMA IF NOT EXISTS springproject1;
use springproject1;
drop table if exists soft_drink;
create table soft_drink (id integer AUTO_INCREMENT, brand varchar(255) not null, name varchar(255) not null, description varchar(255) not null, primary key (id));