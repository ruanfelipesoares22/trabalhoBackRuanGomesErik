package br.unipar.devbackend.bloguerinho.Service;

import br.unipar.devbackend.bloguerinho.Model.Post;
import br.unipar.devbackend.bloguerinho.Model.Usuario;
import br.unipar.devbackend.bloguerinho.Repository.PostsRepository;
import br.unipar.devbackend.bloguerinho.Repository.UsuariosRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PostService {
    private final PostsRepository repository;

    public PostService(PostsRepository repository) {
        this.repository = repository;
    }

    public Post gravar(Post post) {
        return repository.save(post);
    }

    public List<Post> findAll() {
        return repository.findAll();
    }
    public List<Post> findByConteudoContainingIgnoreCase(String conteudo) {
        return repository.findByConteudoContainingIgnoreCase(conteudo);
    }

    public List<Post> listarPostsCronologico() {
        return repository.findAllByOrderByCreatedAtDesc();
    }
    public List<Post> findAllOrderByCurtidasDesc(){
        return repository.findAllOrderByCurtidasDesc();
    }

    public Post editar(Post post) {
        return repository.save(post);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
