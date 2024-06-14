create table respostas(
    id bigint not null auto_increment,
    mensagem varchar(255) not null,
    topico_id bigint not null,
    dataCriacao datetime not null,
    autor_id bigint not null,
    solucao varchar(100) not null,

    primary key(id)
);