CREATE TABLE IF NOT EXISTS public.pastes
(
    id          serial
    primary key,
    text        varchar(255) not null,
    hash        varchar(255) unique,
    lifetime    timestamp,
    description varchar
    );