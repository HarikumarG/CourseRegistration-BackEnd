create table course(id int(11) primary key,name varchar(40));
insert into course(id,name)values(1,"c");
insert into course(id,name)values(2,"c++");
insert into course(id,name)values(3,"linux");
insert into course(id,name)values(4,"java");
insert into course(id,name)values(5,"pearl");
insert into course(id,name)values(6,"python");
insert into course(id,name)values(7,"php");
insert into course(id,name)values(8,"scilab");
create table registered(regno varchar(20),courseid varchar(11));
create table user(regno varchar(20) primary key,name varchar(50),initial varchar(5),dept varchar(50),year varchar(10),section varchar(10),email varchar(50) not null,password varchar(50),mobileno varchar(20),gender varchar(10),forgotpassword varchar(20));