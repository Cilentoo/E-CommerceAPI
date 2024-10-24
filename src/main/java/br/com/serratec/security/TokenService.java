package br.com.serratec.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import br.com.serratec.model.Cliente;
import br.com.serratec.model.User;

@Service
public class TokenService {
	
	@Value("${api.security.token.secret}")
	private String secret;
	
	public String generateToken(User newUser) {
		try {
			Algorithm algoritmo = Algorithm.HMAC256(secret);
			
			String token = JWT.create()
					.withIssuer("login-auth-api")
					.withSubject(newUser.getEmail())
					.withExpiresAt(this.generateExpirationDate())
					.sign(algoritmo);
			return token;
		} catch(JWTCreationException exception) {
			throw new RuntimeException("Erro durante a autenticação!");
		}
	}
	
	// Valida o token
	public String validaToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("login-auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return null;
        }
    }
	
	// Função que define o tempo para expirar o token
	private Instant generateExpirationDate() {
		return LocalDateTime.now()
				.plusHours(2) // Em horas
				.toInstant(ZoneOffset.of("-03:00"));
	}
	
}
