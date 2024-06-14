create table topicos(
    id bigint not null auto_increment,
    titulo varchar(100) not null,
    mensagem varchar(255) not null,
    dataCriacao datetime not null,
    status varchar(100) not null,
    autor_id bigint not null,
    curso_id bigint not null,
    resposta_id bigint not null,

    primary key(id)
);