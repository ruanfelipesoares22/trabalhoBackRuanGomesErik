package br.unipar.devbackend.bloguerinho.Controller;

import br.unipar.devbackend.bloguerinho.Model.Post;
import br.unipar.devbackend.bloguerinho.Model.Usuario;
import br.unipar.devbackend.bloguerinho.Service.PostService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/posts")
public class PostsController {
    private final PostService postservice;

    public PostsController(PostService postservice) {
        this.postservice = postservice;
    }
    @Operation(
            summary = "Publicar post",
            description = "Publicar novo post no bloguinho."
    )
    @PostMapping // mapeamento do metodo HTTP (POST)
    public ResponseEntity<Post> gravar(@RequestBody Post post) {

        if (post.getCreatedAt() == null) {
            post.setCreatedAt(LocalDateTime.now());
        }


        if (post.getLikesCount() == null) {
            post.setLikesCount(0L);
        }


        Post novopost = postservice.gravar(post);
        return ResponseEntity.ok(novopost);
    }
    @Operation(
            summary = "Listar todos os post",
            description = "Listar todos os post que estao no bloguinho."
    )
    @GetMapping("/todos") // mapeamento do metodo HTTP (GET)
    public List<Post> findAll() {
        return postservice.findAll();
    }
    @Operation(
            summary = "Listar post por ordem cronologica",
            description = "Posts com publicados recentemente aparecem em cima"
    )
    @GetMapping("/cronologico")
    public ResponseEntity<List<Post>> listarPostsCronologico() {
        List<Post> novopost = postservice.listarPostsCronologico();
        return ResponseEntity.ok(novopost);
    }
    @Operation(
            summary = "Listar post por Relevancia",
            description = "Posts com mais curtidas aparecem primeiro"
    )
    @GetMapping("/relevancia")
    public ResponseEntity<List<Post>> findAllOrderByCurtidasDesc() {
        List<Post> novopost = postservice.findAllOrderByCurtidasDesc();
        return ResponseEntity.ok(novopost);
    }

}
