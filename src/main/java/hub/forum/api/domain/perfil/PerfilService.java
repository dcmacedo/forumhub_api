package hub.forum.api.domain.perfil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PerfilService {

    @Autowired
    private PerfilRepository repository;

    @Transactional
    public Long cadastrar(DadosCadastroPerfil dados) {

        Perfil perfil = new Perfil();
        perfil.setNome(dados.nome());
        Perfil savedPerfil = repository.save(perfil);
        return savedPerfil.getId();
    }

    public Page<DadosDetalhamentoPerfil> getAllPerfis(Pageable pageable) {
        Page<Perfil> perfisPage = repository.findAll(pageable);
        return perfisPage.map(perfis -> new DadosDetalhamentoPerfil(
                perfis.getId(), perfis.getNome()));
    }


    public void atualizar(Long perfilId, DadosDetalhamentoPerfil dados) {

        Optional<Perfil> optionalPerfil = repository.findById(perfilId);

        if (optionalPerfil.isEmpty()) {
            throw new IllegalStateException("Perfil não encontrado com ID: " + perfilId);
        }

        Perfil perfil = optionalPerfil.get();

        perfil.setNome(dados.nome());

        repository.save(perfil);
    }

    public boolean existsById(Long perfilId) {
        return repository.existsById(perfilId);
    }
}