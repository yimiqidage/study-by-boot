-- 角色
drop table roles;
create table roles(
                     id bigint,
                     name varchar(50),
                     descn varchar(200)
);
alter table roles add constraint pk_role primary key(id);

-- 用户
drop table users;
create table users(
                     id bigint,
                     username varchar(50),
                     password varchar(50),
                     status integer,
                     descn varchar(200)
);
alter table users add constraint pk_user primary key(`id`);

-- 用户角色连接表
drop table user_role;
create table user_role(
                          user_id bigint,
                          role_id bigint
);
alter table user_role add constraint pk_user_role primary key(user_id, role_id);


-- 插入数据：
insert into users (id,username,password,status) values(1,'zhangsan','000',1);
insert into users (id,username,password,status) values(2,'lisi','111',1);
insert into users (id,username,password,status) values(3,'wangwu','222',1);

insert into roles (id,name) values(1,'ROLE_USER');
insert into roles (id,name) values(2,'ROLE_ADMIN');


insert into user_role (user_id,role_id) values(1,1);
insert into user_role (user_id,role_id) values(2,1);
insert into user_role (user_id,role_id) values(2,2);
insert into user_role (user_id,role_id) values(3,1);
insert into user_role (user_id,role_id) values(3,2);