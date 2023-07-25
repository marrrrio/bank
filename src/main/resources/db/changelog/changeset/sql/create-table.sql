create table if not exists bank
(
    client_id    serial  not null
        constraint bank_pk
            primary key,
    client_name  varchar not null,
    client_money numeric not null,
    client_age   integer not null
);

alter table bank
    owner to username;

create unique index bank_client_id_uindex on bank (client_id);