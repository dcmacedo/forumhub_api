create table topicos(
    id bigint not null auto_increment,
    titulo varchar(100) not null,
    mensagem varchar(255) not null,
    dataCriacao datetime not null,
    status varchar(100) not null,
    autor_id bigint not null,
    curso_id bigint not null,
    resposta_id bigint not null,

    primary key(id),
    constraint fk_topicos_resposta_id foreign key(resposta_id) references respostas(id),
    constraint fk_topicos_curso_id foreign key(curso_id) references cursos(id),
    constraint fk_topicos_autor_id foreign key(autor_id) references usuarios(id)
);