package br.unipar.devbackend.bloguerinho.Repository;

import br.unipar.devbackend.bloguerinho.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuariosRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByNomeContainingIgnoreCase(String nome);
}
