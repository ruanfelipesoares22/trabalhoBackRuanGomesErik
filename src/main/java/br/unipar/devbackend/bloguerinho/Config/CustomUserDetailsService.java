package br.unipar.devbackend.bloguerinho.Config;

import br.unipar.devbackend.bloguerinho.Model.Usuario;
import br.unipar.devbackend.bloguerinho.Repository.UsuariosRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuariosRepository repository;

    public CustomUserDetailsService(UsuariosRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository.findByUsernameIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        // Log para verificar as authorities do usuário
        System.out.println("Usuário carregado: " + usuario.getUsername());
        System.out.println("Authorities: " + usuario.getAuthorities());

        return usuario; // Retorna o próprio Usuario que implementa UserDetails
    }
}