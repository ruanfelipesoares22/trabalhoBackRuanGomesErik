package br.unipar.devbackend.bloguerinho.Controller;

import br.unipar.devbackend.bloguerinho.Service.CurtidasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/posts")
public class CurtidasController {
    @Autowired
    private CurtidasService curtidaService;

    @PostMapping("/{postId}/curtir/{usuarioId}")
    public ResponseEntity<String> curtir(@PathVariable Long postId, @PathVariable Long usuarioId) {
        String msg = curtidaService.curtir(postId, usuarioId);
        return ResponseEntity.ok(msg);
    }

    @DeleteMapping("/{postId}/curtir/{usuarioId}")
    public ResponseEntity<String> removerCurtida(@PathVariable Long postId, @PathVariable Long usuarioId) {
        String msg = curtidaService.removerCurtida(postId, usuarioId);
        return ResponseEntity.ok(msg);
    }
}
