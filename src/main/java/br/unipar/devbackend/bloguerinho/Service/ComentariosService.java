package br.unipar.devbackend.bloguerinho.Service;

import br.unipar.devbackend.bloguerinho.Model.Comentarios;
import br.unipar.devbackend.bloguerinho.Model.Post;
import br.unipar.devbackend.bloguerinho.Model.Usuario;
import br.unipar.devbackend.bloguerinho.Repository.ComentariosRepository;
import br.unipar.devbackend.bloguerinho.Repository.PostsRepository;
import br.unipar.devbackend.bloguerinho.Repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ComentariosService {

    @Autowired
    private ComentariosRepository comentariosrepository;
    @Autowired
    private PostsRepository postRepository;
    @Autowired
    private UsuariosRepository usuarioRepository;

    public List<Comentarios> findAll() {
        return comentariosrepository.findAll();
    }

    public String Comentario(Long postId, Long usuarioId, String textoComentario) {
        Optional<Post> postOpt = postRepository.findById(postId);
        if (postOpt.isEmpty()) return "Erro: Post não existe.";

        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
        if (usuarioOpt.isEmpty()) return "Erro: Usuário não existe.";

        Post post = postOpt.get();
        Usuario usuario = usuarioOpt.get();

        // Verifica se o usuário já comentou
        Optional<Comentarios> existente = comentariosrepository.findByPostAndUsuario(post, usuario);
        if (existente.isPresent()) return "Erro: Usuário já comentou este post.";

        // Cria o comentário
        Comentarios comentario = new Comentarios(post, usuario);
        comentario.setComentario(textoComentario);
        comentario.setCreatedAt(LocalDateTime.now());
        comentariosrepository.save(comentario);

        // Atualiza contador no post
        post.setCommentariesCount(post.getCommentariesCount() + 1);
        postRepository.save(post);

        return "Comentário adicionado com sucesso!";
    }

    public String excluirComentario(Long postId, Long usuarioId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post não existe"));
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não existe"));

        Comentarios comentarios = comentariosrepository.findByPostAndUsuario(post, usuario)
                .orElseThrow(() -> new RuntimeException("Comentário não existe"));

        comentariosrepository.delete(comentarios);

        post.setCommentariesCount(post.getCommentariesCount() - 1);
        postRepository.save(post);
        return "Comentário excluído!";
    }
}

