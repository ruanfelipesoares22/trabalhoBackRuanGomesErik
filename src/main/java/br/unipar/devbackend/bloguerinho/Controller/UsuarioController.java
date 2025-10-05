package br.unipar.devbackend.bloguerinho.Controller;

import br.unipar.devbackend.bloguerinho.Model.Usuario;
import br.unipar.devbackend.bloguerinho.Service.UsuarioService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // avisa que é um controlador (que vai receber as requisições)
@RequestMapping("api/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/todos") // mapeamento do metodo HTTP (GET)
    public List<Usuario> findAll() {
        return usuarioService.findAll();
    }

    @GetMapping("/buscar-por-nome")
    public List<Usuario> findByNome(@RequestParam String nome) {
        return usuarioService.findByNome(nome);
    }

    @PostMapping // mapeamento do metodo HTTP (POST)
    public ResponseEntity<Usuario> gravar(@RequestBody Usuario usuario) {
        Usuario usuarioNovo = usuarioService.gravar(usuario);
        return ResponseEntity.ok(usuarioNovo);
    }

    @PutMapping("/{id}") // mapeamento do metodo HTTP (PUT)
    public ResponseEntity<Usuario> atualizar(@PathVariable Long id,
                                             @RequestBody Usuario usuario) {
        if (!id.equals(usuario.getId())) {
            return ResponseEntity.badRequest().build();
        }
        Usuario usuarioAtualizado = usuarioService.editar(usuario);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping("/{id}") // mapeamento do metodo HTTP (DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
