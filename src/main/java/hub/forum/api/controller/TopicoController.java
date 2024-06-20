package hub.forum.api.controller;

import hub.forum.api.domain.topico.DadosCadastroTopico;
import hub.forum.api.domain.topico.DadosDetalhamentoTopicoAtivo;
import hub.forum.api.domain.topico.TopicoService;
import jakarta.validation.Valid;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTopico dados,
                                    UriComponentsBuilder uriBuilder,
                                    Authentication authentication){

        String usuarioLogado = authentication.name();

        Long topicoId = topicoService.cadastrar(dados, usuarioLogado);

        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topicoId).toUri();
        return ResponseEntity.created(uri)
                .body("Tópico registrado com sucesso. Id: " + topicoId);
    }

    @GetMapping("/ativos")
    public ResponseEntity<Page<DadosDetalhamentoTopicoAtivo>> listarAtivos(
            @RequestParam(required = false) String nomeCurso,
            @RequestParam(required = false) Integer ano,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<DadosDetalhamentoTopicoAtivo> topicoAtivoPage = topicoService.getAllTopicosAtivos(pageable, nomeCurso, ano);
        return ResponseEntity.ok(topicoAtivoPage);
    }

}
