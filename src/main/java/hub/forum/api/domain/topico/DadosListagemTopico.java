package hub.forum.api.domain.topico;

import java.time.LocalDateTime;

public record DadosListagemTopico(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime data_criacao,
        Boolean status,
        String autor,
        String curso) {

    public DadosListagemTopico(Topico topico){
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getData_criacao(),
                topico.isStatus(),
                topico.getAutor(),
                topico.getCurso());
    }
}
