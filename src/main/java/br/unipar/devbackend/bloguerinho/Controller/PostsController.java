package br.unipar.devbackend.bloguerinho.Controller;

import br.unipar.devbackend.bloguerinho.Model.Post;
import br.unipar.devbackend.bloguerinho.Model.Usuario;
import br.unipar.devbackend.bloguerinho.Service.PostService;

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
    @GetMapping("/todos") // mapeamento do metodo HTTP (GET)
    public List<Post> findAll() {
        return postservice.findAll();
    }
    @GetMapping("/cronologico")
    public ResponseEntity<List<Post>> listarPostsCronologico() {
        List<Post> novopost = postservice.listarPostsCronologico();
        return ResponseEntity.ok(novopost);
    }
    @GetMapping("/relevancia")
    public ResponseEntity<List<Post>> findAllOrderByCurtidasDesc() {
        List<Post> novopost = postservice.findAllOrderByCurtidasDesc();
        return ResponseEntity.ok(novopost);
    }

}
