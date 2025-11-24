package br.unipar.devbackend.bloguerinho.Config;

import br.unipar.devbackend.bloguerinho.Model.Usuario;
import br.unipar.devbackend.bloguerinho.Repository.UsuariosRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final UsuariosRepository usuarioRepository;
    private final TokenService tokenService;

    public SecurityFilter(UsuariosRepository usuarioRepository, TokenService tokenService) {
        this.usuarioRepository = usuarioRepository;
        this.tokenService = tokenService;
    }

    /**
     * Extrai o token do header Authorization (Bearer <token>)
     */
    private String getToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            return null;
        }
        return header.substring(7);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        try {
            String token = getToken(request);

            if (token != null) {
                // Extrai o username do token
                String username = tokenService.getSubjectByToken(token);

                // Carrega o usuário do banco
                Usuario user = usuarioRepository.findByUsernameIgnoreCase(username)
                        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

                // Cria o Authentication com authorities
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                // Popula o SecurityContext
                SecurityContextHolder.getContext().setAuthentication(authentication);

                // Logs de debug
                System.out.println("Usuário autenticado pelo filtro: " + user.getUsername());
                System.out.println("Authorities do usuário: " + user.getAuthorities());
            }

        } catch (Exception e) {
            System.out.println("Erro ao validar token: " + e.getMessage());
        }

        // Continua a cadeia de filtros
        filterChain.doFilter(request, response);
    }
}
