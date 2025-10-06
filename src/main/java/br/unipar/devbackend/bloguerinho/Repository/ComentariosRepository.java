package br.unipar.devbackend.bloguerinho.Repository;

import br.unipar.devbackend.bloguerinho.Model.Comentarios;
import br.unipar.devbackend.bloguerinho.Model.Curtidas;
import br.unipar.devbackend.bloguerinho.Model.Post;
import br.unipar.devbackend.bloguerinho.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ComentariosRepository extends JpaRepository<Comentarios, Long> {
    Optional<Comentarios> findByPostAndUsuario(Post post, Usuario usuario);

    List<Post> findByComentarioContainingIgnoreCase(String comentario);
}
