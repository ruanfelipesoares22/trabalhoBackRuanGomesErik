package br.unipar.devbackend.bloguerinho.Controller;

import br.unipar.devbackend.bloguerinho.Config.AuthorizationService;
import br.unipar.devbackend.bloguerinho.Dto.LoginRequest;
import br.unipar.devbackend.bloguerinho.Dto.LoginResponse;
import br.unipar.devbackend.bloguerinho.Model.PermissoesEnum;
import br.unipar.devbackend.bloguerinho.Model.Role;
import br.unipar.devbackend.bloguerinho.Model.Usuario;
import br.unipar.devbackend.bloguerinho.Repository.UsuariosRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthorizationService authorizationService;
    private final UsuariosRepository usuariosRepository;
    public AuthenticationController(AuthorizationService authorizationService,
                                    UsuariosRepository usuariosRepository,
                                    PasswordEncoder passwordEncoder) {
        this.authorizationService = authorizationService;
        this.usuariosRepository = usuariosRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody LoginRequest request) {
        System.out.println("Criando usuário: " + request.username());

        // Verifica se o usuário já existe
        if (usuariosRepository.findByUsernameIgnoreCase(request.username()).isPresent()) {
            return ResponseEntity.badRequest().body("Usuário já existe!");
        }

        // Cria o usuário
        Usuario usuario = new Usuario();
        usuario.setUsername(request.username());
        usuario.setPassword(request.password());
        usuario.setNome(request.username());

        // Cria a role COMUM
        Role role = new Role();
        role.setPermissao(PermissoesEnum.COMUM);

        // Adiciona a role à lista (sem ID, será gerado automaticamente)
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        usuario.setListRoles(roles);

        // Salva no banco
        usuariosRepository.save(usuario);

        System.out.println("Usuário criado com sucesso!");
        return ResponseEntity.ok("Usuário criado com sucesso!");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        System.out.println("Tentativa de login: " + request.username());

        String token = authorizationService.authenticateAndGetToken(
                request.username(),
                request.password()
        );

        System.out.println("Login bem-sucedido!");
        return ResponseEntity.ok(new LoginResponse(token));
    }
    @PostMapping("/register/super")
    public ResponseEntity<String> registerSuper(@RequestBody LoginRequest request) {
        System.out.println("Criando SUPER usuário: " + request.username());

        if (usuariosRepository.findByUsernameIgnoreCase(request.username()).isPresent()) {
            return ResponseEntity.badRequest().body("Usuário já existe!");
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(request.username());
        usuario.setPassword(request.password());
        usuario.setNome(request.username());

        // Cria role SUPER
        Role role = new Role();
        role.setPermissao(PermissoesEnum.SUPER); // Permissão SUPER

        List<Role> roles = new ArrayList<>();
        roles.add(role);
        usuario.setListRoles(roles);

        usuariosRepository.save(usuario);

        System.out.println("SUPER usuário criado!");
        return ResponseEntity.ok("SUPER usuário criado com sucesso!");
    }
}