drop table if exists SECTION;
drop table if exists LINE;
drop table if exists STATION;
drop table if exists MEMBER;
create table if not exists STATION
(
    id bigint auto_increment not null,
    name varchar(255) not null unique,
    primary key(id)
    );

create table if not exists LINE
(
    id bigint auto_increment not null,
    name varchar(255) not null unique,
    color varchar(20) not null unique,
    extra_fare int DEFAULT 0,
    primary key(id)
    );

create table if not exists SECTION
(
    id bigint auto_increment not null,
    line_id bigint not null references LINE(id) on delete cascade on update cascade ,
    up_station_id bigint not null,
    down_station_id bigint not null,
    distance int not null,
    primary key(id),
    foreign key (up_station_id) references STATION(id) on delete cascade on update cascade ,
    foreign key (down_station_id) references STATION(id) on delete cascade on update cascade
    );

create table if not exists MEMBER
(
    id bigint auto_increment not null,
    email varchar(255) not null unique,
    password varchar(255) not null,
    age int not null,
    primary key(id)
    );