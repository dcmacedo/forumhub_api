package hub.forum.api.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UsuarioRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Long cadastrar(DadosCadastroUsuario dados){

        if (dados.login() == null || dados.senha() == null){
            throw new IllegalArgumentException("Login e senha são obrigatórios");
        }

        if (repository.existsByLogin(dados.login())){
            throw new IllegalArgumentException("Email já cadastrado para outro usuário, utilize outro email.");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(dados.nome());
        usuario.setLogin(dados.login());
        usuario.setSenha(passwordEncoder.encode(dados.senha()));
        usuario.setStatus(true);

        Usuario usuarioSalvar = repository.save(usuario);
        return usuarioSalvar.getId();
    }

    public Page<DadosDetalhamentoUsuario> getAllUsers(Pageable pageable) {
        Page<Usuario> usuarioPage = repository.findAll(pageable);
        return usuarioPage.map(usuario -> new DadosDetalhamentoUsuario(
                usuario.getId(), usuario.getNome(), usuario.getLogin(), usuario.getStatus()));
    }

    @Transactional
    public void atualizar(Long usuarioId, DadosDetalhamentoUsuario dados){

        Optional<Usuario> usuarioOptional = repository.findById(usuarioId);
        if (usuarioOptional.isEmpty()){
            throw new IllegalStateException("Usuário com ID: " + usuarioId + " não encontrado!");
        }

        Usuario usuario = usuarioOptional.get();
        usuario.setNome(dados.nome());
        usuario.setLogin(dados.login());
        usuario.setStatus(dados.status() || usuario.getStatus());

        repository.save(usuario);
    }

    @Transactional
    public void deletar(Long usuarioId){

        Optional<Usuario> usuarioOptional = repository.findById(usuarioId);
        if (usuarioOptional.isEmpty()){
            throw new IllegalStateException("Usuário com ID: " + usuarioId + " não encontrado!");
        }

        Usuario usuario = usuarioOptional.get();
        usuario.setStatus(false);

        repository.save(usuario);
    }

    public Usuario findByLogin(String login) {
        Usuario usuario = (Usuario) repository.findByLogin(login);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado com login: " + login);
        }
        return usuario;
    }

    public Optional<DadosDetalhamentoUsuario> detalhar(Long id) {
        Optional<Usuario> optionalUser = repository.findById(id)
                .filter(Usuario::getStatus);
        return optionalUser.map(this::mapToDetalhamentoDto);
    }

    private DadosDetalhamentoUsuario mapToDetalhamentoDto(Usuario usuario) {
        return new DadosDetalhamentoUsuario(
                usuario.getId(),
                usuario.getNome(),
                usuario.getLogin(),
                usuario.getStatus());
    }
}
