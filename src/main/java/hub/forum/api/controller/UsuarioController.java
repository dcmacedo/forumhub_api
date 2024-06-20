package hub.forum.api.controller;

import hub.forum.api.domain.usuario.DadosCadastroUsuario;
import hub.forum.api.domain.usuario.DadosDetalhamentoUsuario;
import hub.forum.api.domain.usuario.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder){

        Long usuarioId = usuarioService.cadastrar(dados);

        var uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuarioId).toUri();

        return ResponseEntity.created(uri).body("Usuário cadastrado. ID: " + usuarioId);
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoUsuario>> listar(Pageable pageable){
        Page<DadosDetalhamentoUsuario> usuarioPage = usuarioService.getAllUsers(pageable);
        return ResponseEntity.ok(usuarioPage);
    }

    @PutMapping("/{id}")
    @SecurityRequirement(name = "bearer-key")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody DadosDetalhamentoUsuario dados){
        usuarioService.atualizar(id, dados);
        return ResponseEntity.ok("Usuário atualizado com sucesso!");
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "bearer-key")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id){
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoUsuario> detalhar(@PathVariable Long id){
        Optional<DadosDetalhamentoUsuario> usuario = usuarioService.detalhar(id);
        return usuario.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
