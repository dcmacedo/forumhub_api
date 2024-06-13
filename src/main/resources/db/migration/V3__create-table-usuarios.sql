create table usuarios(
    id bigint not null auto_increment,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    senha varchar(255) not null,
    perfis_id bigint not null,

    primary key(id),
    constraint fk_usuarios_perfis_id foreign key(perfis_id) references perfis(id)

);