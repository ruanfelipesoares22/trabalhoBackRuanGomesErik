package br.unipar.devbackend.bloguerinho.Controller;

import br.unipar.devbackend.bloguerinho.Model.Comentarios;
import br.unipar.devbackend.bloguerinho.Model.Post;
import br.unipar.devbackend.bloguerinho.Repository.ComentariosRepository;
import br.unipar.devbackend.bloguerinho.Service.ComentariosService;
import br.unipar.devbackend.bloguerinho.Service.CurtidasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/comentarios")
public class ComentariosController {

    @Autowired
    private ComentariosService comentariosService;

    // Envia o texto do comentário no corpo da requisição
    @PostMapping("/{postId}/comentar/{usuarioId}")
    public ResponseEntity<String> comentar(
            @PathVariable Long postId,
            @PathVariable Long usuarioId,
            @RequestBody Comentarios comentarioRequest) {

        String msg = comentariosService.Comentario(postId, usuarioId, comentarioRequest.getComentario());
        return ResponseEntity.ok(msg);
    }

    @DeleteMapping("/{postId}/comentar/{usuarioId}")
    public ResponseEntity<String> excluirComentario(@PathVariable Long postId, @PathVariable Long usuarioId) {
        String msg = comentariosService.excluirComentario(postId, usuarioId);
        return ResponseEntity.ok(msg);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Comentarios>> findAll() {
        return ResponseEntity.ok(comentariosService.findAll());
    }
}
