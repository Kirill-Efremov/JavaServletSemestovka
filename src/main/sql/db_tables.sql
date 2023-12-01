create table file_info
(
    id serial not null primary key,
    original_file_name varchar(100),
    storage_file_name varchar(100) not null,
    size bigint not null,
    type varchar(100)
);
create table users (
                       id serial primary key,
                       first_name varchar(40) not null,
                       last_name varchar(40) not null,
                       password varchar(40) not null,
                       email varchar(40) not null,
                       avatar_id bigint references file_info

);
create table posts
(
    id serial primary key,
    title varchar(40) not null,
    content TEXT not null,
    price bigint not null,
    avatar_id bigint references file_info,
    author bigint not null references users (id)
);
create table  like_posts(
                            id serial primary key,
                            author_id bigint not null references users (id),
                            post_id bigint not null references posts (id)

);
