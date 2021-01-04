drop table if exists users;
drop table if exists posts;
drop table if exists regions;

create table users
(
    id        bigint auto_increment primary key,
    name      varchar(255)     not null,
    surname   varchar(255)     not null,
    role      varchar(45)      not null,
    region_id bigint default 0 null

);
create table posts
(
    id      bigint auto_increment                             primary key,
    content varchar(255)                                      not null,
    created timestamp default now()                           not null,
    updated timestamp default '0000-00-00 00:00:00.000000'    not null,
    user_id bigint                                            null
);

create table regions
(
    id      bigint auto_increment                             primary key,
    name    varchar(45)                                       not null
);




-- INSERT INTO crud_app.user (name, surname, role, region_id) VALUES ('Max', 'Hayday', 'User',null );