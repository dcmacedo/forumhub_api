package hub.forum.api.controller;

import hub.forum.api.domain.usuario.DadosCadastroUsuario;
import hub.forum.api.domain.usuario.UsuarioService;
import hub.forum.api.domain.usuario.DadosDetalhamentoUsuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody DadosDetalhamentoUsuario dados){
        usuarioService.atualizar(id, dados);
        return ResponseEntity.ok("Usuário atualizado com sucesso!");
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id){
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
