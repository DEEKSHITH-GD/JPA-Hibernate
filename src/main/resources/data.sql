
create table course (
    id bigint primary key,
    name varchar(255),
    create_date datetime,
    last_updated_date datetime
);

insert into course(id, name, create_date, last_updated_date) values(10001, 'JPA-Hibernate', now(), now());
insert into course(id, name, create_date, last_updated_date) values(10002, 'Spring Boot', now(), now());
insert into course(id, name, create_date, last_updated_date) values(10003, 'Microservices', now(), now());
commit;

create table passport (
    id bigint primary key,
    number varchar(255)
);

insert into passport(id,number)  values(40001, 'P12345');
insert into passport(id,number)  values(40002, 'P23456');
insert into passport(id,number)  values(40003, 'P34567');

create table student (
    id bigint primary key,
    name varchar(255),
    passport_id bigint,
    foreign key(passport_id) references passport(id)
);

insert into student(id,name,passport_id)  values(20001, 'Ranga', 40001);
insert into student(id,name,passport_id)  values(20002, 'Adam', 40002);
insert into student(id,name,passport_id)  values(20003, 'Jane', 40003);

create table review (
    id bigint primary key,
    rating varchar(255),
    description varchar(255),
    course_id bigint,
    foreign key(course_id) references course(id)
);

insert into review(id,rating,description,course_id)  values(50001, '5', 'Great course',10001);
insert into review(id,rating,description,course_id)  values(50002, '4', 'Wonderful course',10002);
insert into review(id,rating,description,course_id)  values(50003, '5', 'Excellent',10003);