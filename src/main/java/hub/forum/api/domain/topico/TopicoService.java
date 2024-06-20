package hub.forum.api.domain.topico;

import hub.forum.api.domain.curso.Curso;
import hub.forum.api.domain.curso.CursoService;
import hub.forum.api.domain.curso.DadosDetalhamentoCurso;
import hub.forum.api.domain.usuario.DadosDetalhamentoUsuario;
import hub.forum.api.domain.usuario.Usuario;
import hub.forum.api.domain.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CursoService cursoService;

    @Transactional
    public Long cadastrar(DadosCadastroTopico dados, String usuarioLogado){

        if (dados.titulo() == null || dados.mensagem() == null){
            throw new IllegalArgumentException("Título e Mensagem são obrigatórios.");
        }

        Usuario usuario = usuarioService.findByLogin(usuarioLogado);

        if (repository.existsByTituloAndMensagemAndCursoId(dados.titulo(), dados.mensagem(), dados.curso().id())){
            throw new IllegalArgumentException("Combinação de Título, Mensagem e Curso já existe.");
        }

        Long cursoId = dados.curso().id();

        if (cursoId == null || !cursoService.existsById(cursoId)){
            throw new IllegalArgumentException("O ID do Curso não é válido");
        }

        Topico topico = new Topico();
        topico.setTitulo(dados.titulo());
        topico.setMensagem(dados.mensagem());
        topico.setAutor(usuario);
        topico.setCurso(new Curso(cursoId, dados.curso().nome(), dados.curso().categoria()));
        topico.setData_criacao(LocalDateTime.now());
        topico.setStatus(true);

        Topico cadastrarTopico = repository.save(topico);
        return cadastrarTopico.getId();
    }

    public Page<DadosDetalhamentoTopicoAtivo> getAllTopicosAtivos(Pageable pageable, String nomeCurso, Integer ano) {
        Page<Topico> topicoPage;

        topicoPage = repository.findByStatusTrue(pageable);

        return topicoPage.map(topico -> new DadosDetalhamentoTopicoAtivo(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                new DadosDetalhamentoUsuario(topico.getAutor().getId(),topico.getAutor().getNome(),topico.getAutor().getLogin()),
                new DadosDetalhamentoCurso(topico.getCurso().getId(),topico.getCurso().getNome(),topico.getCurso().getCategoria())));
    }
}
