DROP TABLE IF EXISTS MEMBER;

create table member (
  id bigint auto_increment,
  member_id varchar(255),
  name varchar(255),
  team_id bigint,

  primary key (id)
);

DROP TABLE IF EXISTS TEAM;
create table team(
  id bigint auto_increment,
  name varchar(255)
);


DROP TABLE IF EXISTS USERS;
CREATE TABLE USERS(
  id bigint auto_increment,
  name varchar(255),
  user_type_str varchar(10),
  user_group_id bigint,
  created_at TIMESTAMP,
  updated_at TIMESTAMP,

  primary key (id)
);

DROP TABLE IF EXISTS USER_GROUP;
CREATE TABLE USER_GROUP(
  id bigint auto_increment,
  name varchar(255),
  description varchar(255),
  group_type_num int,
  created_at TIMESTAMP,
  updated_at TIMESTAMP,

  primary key (id)
);

DROP TABLE IF EXISTS USERS_DETAIL;
CREATE TABLE USERS_DETAIL(
  id bigint auto_increment,
  user_id bigint,
  zipcode int,
  primary key (id)
);
