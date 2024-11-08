package johnoliveira.progetto_settimanale_u5_w3.tools;

import io.jsonwebtoken.security.Keys;
import johnoliveira.progetto_settimanale_u5_w3.exceptions.UnauthorizedException;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;


import java.util.Date;

@Component
public class JWT {

    @Value("${jwt.secret}")
    private String secretKey;

    // genera token per un singolo user
    public String generateToken(String userId) {

        return Jwts.builder()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .subject(String.valueOf(userId))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
    }

    // verifica tokem
    public void verifyToken(String accessToken) {
        try {
            Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                    .build().parse(accessToken);
            // lancerà diversi tipi di eccezioni in base a diverse possibili variazioni del token

        } catch (Exception ex) {
            throw new UnauthorizedException("Problemi con il token! Per favore effettua di nuovo il login!");
        }
    }

    // ottengo lo userId dal token
    public String getIdFromToken(String accessToken) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parseSignedClaims(accessToken)
                .getPayload().getSubject();
    }
}


