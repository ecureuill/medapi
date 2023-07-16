package ecureuill.medapi.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import ecureuill.medapi.domain.user.User;

@Service
public class TokenService {
    
    @Value("${api.security.token.secret}")
    private String secret;
    
    public String gerarToken(User user){
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                .withIssuer("medApi")
                .withSubject(user.getUsername())
                .withExpiresAt(expireDate())
                .sign(algorithm);

        } catch (JWTCreationException ex) {
            throw new RuntimeException("Error on generate jwt", ex);
            
        }
    }

    public String getSubject(String token) {
        try {
            var algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                .withIssuer("medApi")
                .build()
                .verify(token)
                .getSubject();

        } catch (JWTVerificationException e) {
            throw new RuntimeException("Token verification error", e);
        }
    }


    private Instant expireDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
