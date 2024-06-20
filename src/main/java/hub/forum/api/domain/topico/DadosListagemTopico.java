package hub.forum.api.domain.topico;

import hub.forum.api.domain.curso.DadosDetalhamentoCurso;
import hub.forum.api.domain.usuario.DadosDetalhamentoUsuario;

public record DadosListagemTopico(Long id,
                                  String titulo,
                                  String mensagem,
                                  DadosDetalhamentoUsuario autor,
                                  DadosDetalhamentoCurso curso,
                                  Boolean status
) {
    public DadosListagemTopico(Long id,
                               String titulo,
                               String mensagem,
                               DadosDetalhamentoUsuario autor,
                               DadosDetalhamentoCurso curso,
                               Boolean status) {
        this.id = id;
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.autor = autor;
        this.curso = curso;
        this.status = status;
    }}
