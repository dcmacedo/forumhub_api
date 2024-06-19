package hub.forum.api.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuario(
        @NotBlank String nome,
        @NotBlank @Email String login,
        @NotBlank String senha
) {
}
