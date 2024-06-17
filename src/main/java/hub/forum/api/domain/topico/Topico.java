package hub.forum.api.domain.topico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensagem;
    private LocalDateTime data_criacao;
    private boolean status;
    private String autor;
    private String curso;
    private String resposta;

    public void atualizarInformacaoes(DadosAtualizacaoTopico dados) {
        if (dados.titulo() != null) {
            this.titulo = dados.titulo();
        }

        if (dados.mensagem() != null) {
            this.mensagem = dados.mensagem();
        }

        if (dados.autor() != null) {
            this.autor = dados.autor();
        }

        if (dados.curso() != null) {
            this.curso = dados.curso();
        }
    }
}
