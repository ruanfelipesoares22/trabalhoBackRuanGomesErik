package br.unipar.devbackend.bloguerinho.Repository;

import br.unipar.devbackend.bloguerinho.Model.Curtidas;
import br.unipar.devbackend.bloguerinho.Model.Post;
import br.unipar.devbackend.bloguerinho.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurtidasRepository extends JpaRepository<Curtidas, Long> {
    Optional<Curtidas> findByPostAndUsuario(Post post, Usuario usuario);
}