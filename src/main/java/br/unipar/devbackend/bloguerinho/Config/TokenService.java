package br.unipar.devbackend.bloguerinho.Config;


import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration-time}")
    private Long expirationTime;

    public String gerarToken(UserDetails user){
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return com.auth0.jwt.JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(algorithm);
    }

    public String getSubjectByToken(String token ){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return com.auth0.jwt.JWT.require(algorithm)
                    .build()
                    .verify(token).getSubject();
        }catch(Exception e){
            return e.getMessage();
        }

    }
}
