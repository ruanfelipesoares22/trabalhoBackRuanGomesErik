package br.unipar.devbackend.bloguerinho.Service;

import br.unipar.devbackend.bloguerinho.Model.Curtidas;
import br.unipar.devbackend.bloguerinho.Model.Post;
import br.unipar.devbackend.bloguerinho.Model.Usuario;
import br.unipar.devbackend.bloguerinho.Repository.CurtidasRepository;
import br.unipar.devbackend.bloguerinho.Repository.PostsRepository;
import br.unipar.devbackend.bloguerinho.Repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CurtidasService {
    @Autowired
    private CurtidasRepository curtidaRepository;

    @Autowired
    private PostsRepository postRepository;

    @Autowired
    private UsuariosRepository usuarioRepository;

    public String curtir(Long postId, Long usuarioId) {
        // Verifica se o post existe
        Optional<Post> postOpt = postRepository.findById(postId);
        if (postOpt.isEmpty()) {
            return "Erro: Post não existe.";
        }
        Post post = postOpt.get();

        // Verifica se o usuário existe
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
        if (usuarioOpt.isEmpty()) {
            return "Erro: Usuário não existe.";
        }
        Usuario usuario = usuarioOpt.get();

        // Verifica se o usuário já curtiu o post
        Optional<Curtidas> existente = curtidaRepository.findByPostAndUsuario(post, usuario);
        if (existente.isPresent()) {
            return "Erro: Usuário já curtiu este post.";
        }

        // Cria a curtida
        Curtidas curtida = new Curtidas(post, usuario);
        curtidaRepository.save(curtida);

        // Atualiza contador de likes
        post.setLikesCount(post.getLikesCount() + 1);
        postRepository.save(post);

        return "Post curtido com sucesso!";
    }

    public String removerCurtida(Long postId, Long usuarioId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post não existe"));
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não existe"));

        Curtidas curtida = curtidaRepository.findByPostAndUsuario(post, usuario)
                .orElseThrow(() -> new RuntimeException("Curtida não existe"));

        curtidaRepository.delete(curtida);

        post.setLikesCount(post.getLikesCount() - 1);
        postRepository.save(post);
        return "Curtida removida!";
    }
}
