package hub.forum.api.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosCadastroTopico(
        @NotBlank String titulo,
        @NotBlank String mensagem,
        @NotNull LocalDateTime dataCriacao,
        @NotBlank  String status,
        @NotBlank  String autor,
        @NotBlank  String curso
) {
}
