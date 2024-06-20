package hub.forum.api.domain.topico;

import hub.forum.api.domain.usuario.DadosDetalhamentoUsuario;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroTopico(
        @NotBlank String titulo,
        @NotBlank String mensagem,
        DadosDetalhamentoUsuario autor,
        @NotBlank String nomeCurso) {
}
