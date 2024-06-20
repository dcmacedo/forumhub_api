package hub.forum.api.domain.topico;

import hub.forum.api.domain.curso.DadosDetalhamentoCurso;
import hub.forum.api.domain.resposta.DadosDetalhamentoResposta;
import hub.forum.api.domain.usuario.DadosDetalhamentoUsuario;

import java.util.List;

public record DadosDetalhamentoTopico(Long id,
                                      String titulo,
                                      String mensagem,
                                      DadosDetalhamentoUsuario autor,
                                      DadosDetalhamentoCurso curso,
                                      List<DadosDetalhamentoResposta> resposta,
                                      Boolean status
) {
    public DadosDetalhamentoTopico(Long id,
                                   String titulo,
                                   String mensagem,
                                   DadosDetalhamentoUsuario autor,
                                   DadosDetalhamentoCurso curso,
                                   List<DadosDetalhamentoResposta> resposta,
                                   Boolean status) {
        this.id = id;
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.autor = autor;
        this.curso = curso;
        this.resposta = resposta;
        this.status = status;
    }}
