create table account
(
    id           bigint auto_increment comment '아이디'
        primary key,
    user_id      varchar(45)  not null comment '회원 아이디',
    password     varchar(16)  not null comment '패스워드',
    name         varchar(60)  not null comment '이름',
    phone_number varchar(20)  not null comment '휴대폰번호',
    email        varchar(100) not null comment '이메일',
    status       varchar(10)  not null comment '상태',
    user_level   varchar(10)  not null comment '사용자 레벨',
    created_at   datetime     not null comment '회원 가입일',
    updated_at   datetime     not null comment '회원 수정일'
)
    comment '회원';

create index account_id_password_index
    on account (id, password);

create index account_id_user_id_name_phone_number_email_index
    on account (id, user_id, name, phone_number, email);

create table region
(
    id     bigint auto_increment comment '아이디'
        primary key,
    name   varchar(255) not null comment '이름',
    status varchar(10)  not null comment '상태'
)
    comment '지역';

create index region_id_index
    on region (id);

create index region_name_index
    on region (name);

create table terminal
(
    id        bigint auto_increment comment '아이디'
        primary key,
    name      varchar(255) not null comment '이름',
    address   varchar(255) not null comment '주소',
    tel       varchar(100) not null comment '전화번호',
    region_id bigint       not null comment '지역 아이디',
    status    varchar(10)  null,
    constraint terminal_region_id_fk
        foreign key (region_id) references region (id)
            on update cascade on delete cascade
)
    comment '터미널';

create index terminal_id_name_region_id_index
    on terminal (id, name, region_id);

