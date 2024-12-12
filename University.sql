CREATE DATABASE university_system;
use university_system;
CREATE table Course(Course_code varchar(20) not null Primary key,Name varchar(30),Credit_hrs int,
Semester int,Type varchar(20),instructor varchar(40),dept varchar(30));
alter table course modify column Course_code varchar(20);
alter table course drop primary key;
desc course;
select *from Course;
CREATE table all_users(type varchar(15),id varchar(20),Name varchar(30),password varchar(12),dob varchar(20),semester int);
alter table all_users add constraint pk_id primary key (id);
select *from all_users;