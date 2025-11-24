package br.unipar.devbackend.bloguerinho.Controller;

import br.unipar.devbackend.bloguerinho.Model.Usuario;
import br.unipar.devbackend.bloguerinho.Service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

    @Operation(
            summary = "Lista todos os usuários",
            description = "Retorna todos os usuários cadastrados no bloguinho."
    )
    @GetMapping("/todos") // mapeamento do metodo HTTP (GET)
    public List<Usuario> findAll() {
        return usuarioService.findAll();
    }

    @Operation(
            summary = "Buscar usuario por nome",
            description = "Retorna o usuario por nome no  bloguinho."
    )

    @GetMapping("/buscar-por-nome")
    public Usuario findByNome(
            @Parameter(
                    description = "Nome do usuário que será buscado",
                    example = "Ruan"
            )
            @RequestParam String nome) {

        return usuarioService.findByUsername(nome);
    }
    @Operation(
            summary = "Gravar novo usuario",
            description = "Grava novo usuario no  bloguinho."
    )
    @PostMapping // mapeamento do metodo HTTP (POST)
    public ResponseEntity<Usuario> gravar(
            @Parameter(
                    description = "Informaceos do novo usuario",
                    example = "1"
            )
            @RequestBody Usuario usuario) {
        Usuario usuarioNovo = usuarioService.gravar(usuario);
        return ResponseEntity.ok(usuarioNovo);
    }

    @Operation(
            summary = "Atualizar usuario",
            description = "Atualiza informacaoes de usuario no  bloguinho."
    )
    @PutMapping("/{id}") // mapeamento do metodo HTTP (PUT)
    public ResponseEntity<Usuario> atualizar(
            @Parameter(
                    description = "Id do usuario que sera atualizado",
                    example = "1"
            )
            @PathVariable Long id,
            @RequestBody Usuario usuario) {
        if (!id.equals(usuario.getId())) {
            return ResponseEntity.badRequest().build();
        }
        Usuario usuarioAtualizado = usuarioService.editar(usuario);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @Operation(
            summary = "Deletar usuario",
            description = "Deleta o usuario cadastrado no  bloguinho."
    )
    @DeleteMapping("/{id}") // mapeamento do metodo HTTP (DELETE)
    public ResponseEntity<Void> delete(
            @Parameter(
                    description = "Id do usuario que sera Deletado",
                    example = "1"
            )@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
