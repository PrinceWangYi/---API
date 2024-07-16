-- auto-generated definition
create table article
(
    post_id       int auto_increment
        primary key,
    title         varchar(128)                       not null comment '标题',
    content       varchar(256)                       not null comment '内容',
    user_id       int                                not null comment '用户ID',
    created       datetime default CURRENT_TIMESTAMP null invisible,
    last_modified datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    constraint user_fk
        foreign key (user_id) references user (user_id)
)
    comment '文章表';

-- auto-generated definition
create table user
(
    user_id       int auto_increment
        primary key,
    username      varchar(64)                        not null,
    password      varchar(64)                        not null,
    email         varchar(64)                        null comment '邮箱',
    created       datetime default CURRENT_TIMESTAMP null comment '创建时间',
    last_modified datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '用户表';

-- auto-generated definition
create table log
(
    log_id         int auto_increment
        primary key,
    request_url    varchar(64)  not null,
    exception_code varchar(10)  null comment '异常状态码',
    exception_msg  varchar(256) null comment '异常信息'
)
    comment '日志记录表';



