package hub.forum.api.domain.curso;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository repository;


    @Transactional
    public Long cadastrar(DadosCadastroCurso dados) {

        if (dados.nome() == null || dados.categoria() == null) {
            throw new IllegalArgumentException("Nome e categoria são obrigatórios");
        }

        Curso curso = new Curso();
        curso.setNome(dados.nome());
        curso.setCategoria(dados.categoria());
        Curso savedCurso = repository.save(curso);
        return savedCurso.getId();
    }

    public Page<DadosDetalhamentoCurso> getAllCursos(Pageable pageable) {
        Page<Curso> cursosPage = repository.findAll(pageable);
        return cursosPage.map(cursos -> new DadosDetalhamentoCurso(
                cursos.getId(), cursos.getNome(), cursos.getCategoria()));
    }


    public void atualizar(Long cursoId, DadosDetalhamentoCurso dados) {
        Optional<Curso> optionalCurso = repository.findById(cursoId);
        if (optionalCurso.isEmpty()) {
            throw new IllegalStateException("Curso não encontrado com ID: " + cursoId);
        }
        Curso curso = optionalCurso.get();

        curso.setNome(dados.nome());
        curso.setCategoria(dados.categoria());

        repository.save(curso);
    }

    public boolean existsById(Long cursoId) {
        return repository.existsById(cursoId);
    }

    public Curso buscarCurso(String nomeCurso){
        return repository.localizarCurso(nomeCurso);
    }
}