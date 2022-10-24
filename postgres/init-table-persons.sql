CREATE TABLE IF NOT EXISTS public.pastes
(
    id          serial
    primary key,
    text        varchar(255) not null,
    hash        integer      not null
    unique,
    lifetime    timestamp,
    is_public   boolean      not null,
    description varchar
    );