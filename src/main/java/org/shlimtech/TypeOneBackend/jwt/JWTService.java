package org.shlimtech.TypeOneBackend.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;

@Service
@NoArgsConstructor
public class JWTService {

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(int userId) {
        Date expirationDate = Date.from(ZonedDateTime.now().plusMinutes(60).toInstant());

        return JWT.create()
                .withSubject("User details")
                .withClaim("userId", userId)
                .withIssuedAt(new Date())
                .withIssuer("timattt")
                .withExpiresAt(expirationDate)
                .sign(Algorithm.HMAC256(secret));
    }

    public int validateTokenAndRetrieveClaim(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("User details")
                .withIssuer("timattt")
                .build();

        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("userId").asInt();
    }

}
