package hub.forum.api.domain.perfil;

import hub.forum.api.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Table(name= "perfis")
@Entity(name = "Perfil")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @ManyToMany(mappedBy = "perfis")
    private Set<Usuario> usuarios = new HashSet<>();

}