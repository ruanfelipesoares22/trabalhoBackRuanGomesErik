package br.unipar.devbackend.bloguerinho.Controller;

import br.unipar.devbackend.bloguerinho.Service.CurtidasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/posts")
public class CurtidasController {
    @Autowired
    private CurtidasService curtidaService;
    @Operation(
            summary = "Curtir post",
            description = "Curtir post, uma curtida por usuario somente"
    )
    @PostMapping("/{postId}/curtir/{usuarioId}")
    public ResponseEntity<String> curtir(
            @Parameter(
            description = "Id do post que sera curtido",
            example = "20"
            )
            @PathVariable Long postId,
            @Parameter(
                    description = "Id do usuario para vincular a curtida",
                    example = "4"
            )
            @PathVariable Long usuarioId) {
        String msg = curtidaService.curtir(postId, usuarioId);
        return ResponseEntity.ok(msg);
    }
    @Operation(
            summary = "Deletar curtidade do post",
            description = "Deletar curtida do post"
    )
    @DeleteMapping("/{postId}/curtir/{usuarioId}")
    public ResponseEntity<String> removerCurtida(
            @Parameter(
                    description = "Id do post que sera removida a curtida",
                    example = "20"
            )
            @PathVariable Long postId,
            @Parameter(
                    description = "Id do usuario para vincular a curtida",
                    example = "03"
            )
            @PathVariable Long usuarioId) {
        String msg = curtidaService.removerCurtida(postId, usuarioId);
        return ResponseEntity.ok(msg);
    }
}
