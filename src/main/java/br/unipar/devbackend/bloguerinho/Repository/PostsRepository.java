package br.unipar.devbackend.bloguerinho.Repository;

import br.unipar.devbackend.bloguerinho.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Post, Long> {
    List<Post> findByConteudoContainingIgnoreCase(String conteudo);

    List<Post> findAllByOrderByCreatedAtDesc();

    @Query("SELECT p FROM Post p ORDER BY p.likesCount DESC")
    List<Post> findAllOrderByCurtidasDesc();
}
