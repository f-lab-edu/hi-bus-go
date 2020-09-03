create table account
(
    id           bigint auto_increment comment '아이디'
        primary key,
    user_id      varchar(255) not null comment '회원 아이디(이메일)',
    password     varchar(30)  not null comment '패스워드',
    name         varchar(60)  not null comment '이름',
    phone_number varchar(20)  not null comment '휴대폰번호',
    status       varchar(10)  not null comment '상태',
    user_level   varchar(10)  not null comment '사용자 레벨',
    created_at   datetime     not null comment '회원 가입일',
    updated_at   datetime     not null comment '회원 수정일',
    constraint account_user_id_uindex
        unique (user_id)
)
    comment '회원';

create index account_id_name_index
    on account (id, name);

create table mileage
(
    id         bigint auto_increment comment '아이디'
        primary key,
    account_id bigint      not null comment '회원 아이디',
    mileage    bigint      not null comment '마일리지',
    status     varchar(10) not null comment '상태',
    created_at datetime    not null comment '추가일',
    updated_at datetime    not null comment '수정일',
    constraint mileage_account_id_fk
        unique (account_id),
    constraint mileage_account_id_fk
        foreign key (account_id) references account (id)
            on update cascade on delete cascade
)
    comment '마일리지 정보';

create table region
(
    id         bigint auto_increment comment '아이디'
        primary key,
    name       varchar(255) not null comment '이름',
    status     varchar(10)  not null comment '상태',
    create_at  datetime     not null comment '추가일',
    updated_at datetime     not null comment '수정일'
)
    comment '지역';

create index region_id_name_index
    on region (id, name);

create table terminal
(
    id         bigint auto_increment comment '아이디'
        primary key,
    name       varchar(255) not null comment '이름',
    address    varchar(255) not null comment '주소',
    tel        varchar(30)  not null comment '전화번호',
    region_id  bigint       not null comment '지역 아이디',
    status     varchar(10)  null,
    created_at datetime     not null comment '추가일',
    updated_at datetime     not null comment '수정일',
    constraint terminal_region_id_fk
        foreign key (region_id) references region (id)
            on update cascade on delete cascade
)
    comment '터미널';

create table arrive_terminal
(
    id          bigint auto_increment comment '도착 터미널 아이디'
        primary key,
    terminal_id bigint   not null comment '터미널 아이디',
    create_at   datetime not null comment '추가일',
    update_at   datetime not null comment '추가일',
    constraint arrive_terminal_terminal_id_uindex
        unique (terminal_id),
    constraint arrive_terminal_terminal_id_fk
        foreign key (terminal_id) references terminal (id)
            on update cascade on delete cascade
)
    comment '도착 터미널';

create table departure_terminal
(
    id          bigint auto_increment comment '출발 터미널 아이디'
        primary key,
    terminal_id bigint   not null comment '터미널 아이디',
    create_at   datetime not null comment '추가일',
    updated_at  datetime not null comment '수정일',
    constraint departure_terminal_terminal_id_uindex
        unique (terminal_id),
    constraint departure_terminal_terminal_id_fk
        foreign key (terminal_id) references terminal (id)
            on update cascade on delete cascade
)
    comment '출발 터미널';

create table route
(
    id                    bigint auto_increment
        primary key,
    name                  varchar(255) not null comment '노선 이름',
    distance              bigint       not null comment '거리',
    seat_residual         bigint       not null comment '잔여석',
    grade                 varchar(20)  null comment '노선 등급',
    departure_terminal_id bigint       not null comment '출발 터미널 아이디',
    arrive_terminal_id    bigint       not null comment '도착 터미널 아이디',
    departure_time        datetime     not null comment '출발 시간',
    arrive_terminal       datetime     not null comment '도착 시간',
    created_at            datetime     not null comment '추가일',
    updated_at            datetime     not null,
    status                varchar(10)  not null comment '상태',
    constraint route_arrive_terminal_id_uindex
        unique (arrive_terminal_id),
    constraint route_departure_terminal_id_uindex
        unique (departure_terminal_id),
    constraint route_arrive_terminal_id_fk
        foreign key (arrive_terminal_id) references arrive_terminal (id)
            on update cascade on delete cascade,
    constraint route_departure_terminal_id_fk
        foreign key (departure_terminal_id) references departure_terminal (id)
            on update cascade on delete cascade
)
    comment '노선';

create index route_name_index
    on route (name);

create index terminal_id_name_region_id_index
    on terminal (id, name, region_id);

