CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    senha VARCHAR(255) NOT NULL,
    status BOOLEAN NOT NULL
);

CREATE TABLE perfis (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

CREATE TABLE usuario_perfil (
    usuario_id BIGINT,
    perfil_id BIGINT,
    PRIMARY KEY (usuario_id, perfil_id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (perfil_id) REFERENCES perfis(id)
);


CREATE TABLE cursos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    categoria VARCHAR(255) NOT NULL
);


CREATE TABLE topicos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    mensagem TEXT NOT NULL,
    data_criacao TIMESTAMP,
    status BOOLEAN NOT NULL,
    autor_id BIGINT,
    curso_id BIGINT,
    FOREIGN KEY (autor_id) REFERENCES usuarios(id),
    FOREIGN KEY (curso_id) REFERENCES cursos(id)
);

CREATE TABLE respostas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    mensagem TEXT NOT NULL,
    data_criacao TIMESTAMP,
    solucao BOOLEAN NOT NULL,
    autor_id BIGINT,
    topico_id BIGINT,
    FOREIGN KEY (autor_id) REFERENCES usuarios(id),
    FOREIGN KEY (topico_id) REFERENCES topicos(id)
);