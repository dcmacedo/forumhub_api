package hub.forum.api.controller;

import hub.forum.api.domain.ValidacaoException;
import hub.forum.api.domain.topico.DadosCadastroTopico;
import hub.forum.api.domain.topico.DadosDetalhamentoTopico;
import hub.forum.api.domain.topico.DadosAtualizacaoTopico;
import hub.forum.api.domain.topico.DadosListagemTopico;
import hub.forum.api.domain.topico.Topico;
import hub.forum.api.domain.topico.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTopico dados,
                                    UriComponentsBuilder uriBuilder){
        var topico = new Topico(null, dados.titulo(), dados.mensagem(), LocalDateTime.now(),
                true, dados.autor(), dados.curso(),null);

        Optional<Topico> optionalTopico = repository.findByTituloAndMensagem(dados.titulo(), dados.mensagem());

        if (optionalTopico.isPresent()) {
            throw new ValidacaoException("Não é possivel registrar um topico com o mesmo titulo e mensagem");
        } else {
            repository.save(topico);
        }

        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listar(@PageableDefault(size = 10) Pageable paginacao){
        var page = repository.findAllByOrderByDataCriacaoAsc(paginacao).map(DadosListagemTopico::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var topico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoTopico dados){
        var topico = repository.getReferenceById(id);
        topico.atualizarInformacaoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        if (repository.existsById(id)){
            repository.deleteById(id);
        }
        return ResponseEntity.noContent().build();
    }
}
