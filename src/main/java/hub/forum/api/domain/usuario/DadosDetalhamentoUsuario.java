package hub.forum.api.domain.usuario;

public record DadosDetalhamentoUsuario(
        Long id,
        String nome,
        String login,
        Boolean status) {

}
