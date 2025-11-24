package br.unipar.devbackend.bloguerinho.Config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthorizationService(AuthenticationManager authenticationManager,
                                TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public String authenticateAndGetToken(String username, String password) {
        System.out.println("Iniciando autenticação para: " + username);

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        System.out.println("Autenticação OK!");

        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String token = tokenService.gerarToken(userDetails);

        System.out.println("Token gerado!");

        return token;
    }
}