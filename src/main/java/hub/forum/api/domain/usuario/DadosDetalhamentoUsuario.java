package hub.forum.api.domain.usuario;

public record DadosDetalhamentoUsuario(
        Long id,
        String nome,
        String login,
        Boolean status) {

    public DadosDetalhamentoUsuario(Long id, String nome, String login) {
        this(id,nome,login,false);
    }
}
