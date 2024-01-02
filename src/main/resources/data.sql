create table course (
    id bigint primary key,
    name varchar(255)
);

insert into course(id, name) values(10001, 'JPA-Hibernate');
insert into course(id, name) values(10002, 'Spring Boot');
insert into course(id, name) values(10003, 'Microservices');
commit;