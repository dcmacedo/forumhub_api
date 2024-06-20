package hub.forum.api.domain.resposta;

import hub.forum.api.domain.topico.Topico;
import hub.forum.api.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record DadosCadastroResposta(String mensagem, Boolean solucao, Long autorId, Long topicoId) {
    public Resposta toEntity(Usuario autor, Topico topico, LocalDateTime dataCriacao){
        Resposta resposta = new Resposta();
        resposta.setMensagem(this.mensagem());
        resposta.setSolucao(this.solucao());
        resposta.setAutor(autor);
        resposta.setTopico(topico);
        resposta.setData_criacao(dataCriacao);
        return resposta;
    }
}
