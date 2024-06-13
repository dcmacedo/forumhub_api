create table respostas(
    id bigint not null auto_increment,
    mensagem_id varchar(255) not null,
    topico_id bigint not null,
    dataCriacao datetime not null,
    autor_id bigint not null,
    solucao varchar(100) not null,

    primary key(id),
    constraint fk_respostas_autor_id foreign key(autor_id) references usuarios(id)

);