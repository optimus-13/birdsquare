drop database if exists birdsquare;

create database birdsquare;

use birdsquare;

-- note: don't use indentations or shell script won't run
create table bird (
id int not null auto_increment,
name varchar(200) not null,
PRIMARY KEY ( id )
);