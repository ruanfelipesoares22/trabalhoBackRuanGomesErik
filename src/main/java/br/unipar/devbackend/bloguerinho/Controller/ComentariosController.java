package br.unipar.devbackend.bloguerinho.Controller;

import br.unipar.devbackend.bloguerinho.Model.Comentarios;
import br.unipar.devbackend.bloguerinho.Model.Post;
import br.unipar.devbackend.bloguerinho.Repository.ComentariosRepository;
import br.unipar.devbackend.bloguerinho.Service.ComentariosService;
import br.unipar.devbackend.bloguerinho.Service.CurtidasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/comentarios")
public class ComentariosController {

    @Autowired
    private ComentariosService comentariosService;

    @Operation(
            summary = "Gravar comentario no post",
            description = "Publicar comentario em um post."
    )
    @PostMapping("/{postId}/comentar/{usuarioId}")
    public ResponseEntity<String> comentar(
            @Parameter(
                    description = "Id do post que sera comentado",
                    example = "20"
            )
            @PathVariable Long postId,
            @Parameter(
                    description = "Id do usuario para vincular o comentario",
                    example = "03"
            )
            @PathVariable Long usuarioId,
            @RequestBody Comentarios comentarioRequest) {

        String msg = comentariosService.Comentario(postId, usuarioId, comentarioRequest.getComentario());
        return ResponseEntity.ok(msg);
    }
    @Operation(
            summary = "Deletar  comentario no post",
            description = "Deletar comentario publicado em um post"
    )
    @DeleteMapping("/{postId}/comentar/{usuarioId}")
    public ResponseEntity<String> excluirComentario(
            @Parameter(
                    description = "Id do post que sera removida o comentado",
                    example = "38"
            )
            @PathVariable Long postId,
            @Parameter(
                    description = "Id do usuario para vincular o comentario",
                    example = "42"
            )
            @PathVariable Long usuarioId) {
        String msg = comentariosService.excluirComentario(postId, usuarioId);
        return ResponseEntity.ok(msg);
    }
    @Operation(
            summary = "Listar todos os comentarios",
            description = "Listar todos os do bloguinho"
    )
    @GetMapping("/todos")
    public ResponseEntity<List<Comentarios>> findAll() {
        return ResponseEntity.ok(comentariosService.findAll());
    }
}
