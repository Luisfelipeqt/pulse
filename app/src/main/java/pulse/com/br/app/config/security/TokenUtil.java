package pulse.com.br.app.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import pulse.com.br.app.core.entities.User;

import java.io.IOException;


import java.security.Key;
import java.util.Collections;
import java.util.Date;

public class TokenUtil {

    public static final long SEGUNDOS = 1000;
    public static final long MINUTOS = SEGUNDOS * 60;
    public static final long HORAS = MINUTOS * 60;
    public static final long DIAS = HORAS * 24;
    public static final long EXPIRATION = DIAS * 5;

    public static final String ISSUER = "Pulse";

    public static final String SECRET_KEY = "35201874963582035015624856398521";

    public static final String PREFIX = "Bearer ";


    public static AuthToken encode(User user){
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        String jws = Jwts.builder().setSubject(user.getLogin())
                .setIssuer(ISSUER)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION ))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        return new AuthToken(PREFIX + jws, EXPIRATION / 86400000 );
    }

    public static Authentication decode(HttpServletRequest request) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        token = token.replace(PREFIX, "");
        Jws<Claims> claimsJws;
        claimsJws = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY.getBytes())
                .build()
                .parseClaimsJws(token);

        String subject = claimsJws.getBody().getSubject();
        String issuer = claimsJws.getBody().getIssuer();
        Date expiration = claimsJws.getBody().getExpiration();
        if(isValid(subject, issuer, expiration)) {
            return new UsernamePasswordAuthenticationToken(
                    subject,
                    null,
                    Collections.emptyList());
        }
        return null;
    }

    public static boolean isValid(String subject, String issuer, Date expiration) {
        if(subject != null && subject.length() > 0 && issuer.equals(ISSUER) && expiration.after(new Date(System.currentTimeMillis()))){
            return true;
        }
        return false;
    }
}