CREATE DATABASE university_system;
use university_system;
CREATE table Course(Course_code varchar(20) not null Primary key,Name varchar(30),Credit_hrs int,
Semester int,Type varchar(20),instructor varchar(40),dept varchar(30));
alter table course modify column Course_code varchar(20);
alter table course drop primary key;
desc course;
update course set Semester=1 where Semester=2;
select *from Course;
CREATE table all_users(type varchar(15),id varchar(20),Name varchar(30),password varchar(12),dob varchar(20),semester int);
alter table all_users add constraint pk_id primary key (id);
alter table all_users add department varchar(30);
alter table all_users change column Name user_name varchar(30);
select *from all_users;
desc all_users;
select *from course inner join all_users on course.Semester=all_users.semester and course.dept=all_users.department and all_users.type="STUDENT";
create table attendance_info(Attend_name varchar(30),id varchar(30),course_name varchar(30),
attendance varchar(10),DateOfAttendance varchar(20));
