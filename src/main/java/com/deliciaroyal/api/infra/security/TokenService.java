package com.deliciaroyal.api.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.deliciaroyal.api.domain.funcionario.Funcionario;

@Service
public class TokenService {
	
	@Value("${api.security.token.secret}")
	private String secret;
	
	public String gerarToken(Funcionario funcionario) {
		try {
		    var algoritmo = Algorithm.HMAC256(secret);
		    return JWT.create()
		        .withIssuer("API VOLL.med")
		        .withSubject(funcionario.getLogin())
		        .withClaim("Username", funcionario.getUsername())
		        .withExpiresAt(dataExpiracao())
		        .sign(algoritmo);
		} catch (JWTCreationException exception){
		    throw new RuntimeException("erro ao gerar token jwt", exception);
		}
	}
	
	public String getSubject(String tokenJWT) {
		try {
			var algoritmo = Algorithm.HMAC256(secret);
		    return JWT.require(algoritmo)
		        .withIssuer("API VOLL.med")
		        .build()
		        .verify(tokenJWT)
		        .getSubject();
		} catch (JWTVerificationException exception){
			throw new RuntimeException("Token JWT inválido ou expirado");
		}
	}

	private Instant dataExpiracao() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
}
