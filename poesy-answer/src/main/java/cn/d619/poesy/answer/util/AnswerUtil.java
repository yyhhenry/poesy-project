package cn.d619.poesy.answer.util;

import java.security.Key;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class AnswerUtil {
    @Value("${SECRET_KEY}")
    private String secret;
    private Key key;

    @PostConstruct
    public void init() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        key = Keys.hmacShaKeyFor(keyBytes);
    }

    public void validateTokenWithType(String token, String type) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            if (!claims.get("type").equals(type)) {
                throw new HttpException(HttpStatus.UNAUTHORIZED, "Expected " + type + " token");
            }
        } catch (Exception e) {
            throw new HttpException(HttpStatus.UNAUTHORIZED, "Invalid token");
        }
    }

    public String getEmailFromToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            throw new HttpException(HttpStatus.UNAUTHORIZED, "Invalid token");
        }
    }
}
