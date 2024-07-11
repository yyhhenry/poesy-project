package cn.d619.poesy.user.util;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.d619.poesy.user.exception.AuthException;
import cn.d619.poesy.user.pojo.dto.TokenInfoDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtUtil {
    @Value("${SECRET_KEY}")
    private String secret;

    private static final long ACCESS_EXPIRATION_TIME = 65 * 1000; // 15 minutes
    private static final long REFRESH_EXPIRATION_TIME = 15 * 24 * 60 * 60 * 1000; // 15 days

    private Key key;

    @PostConstruct
    public void init() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        key = Keys.hmacShaKeyFor(keyBytes);
    }

    public Long generateExpireTime(String type) {
        return System.currentTimeMillis() + (type.equals("refresh") ? REFRESH_EXPIRATION_TIME : ACCESS_EXPIRATION_TIME);
    }

    public String generateToken(String email, String type, Long ExpireTime) {
        return Jwts.builder()
                .setSubject(email)
                .claim("type", type)
                .setExpiration(new Date(ExpireTime))
                .signWith(key)
                .compact();
    }

    public void validateTokenWithType(String token, String type) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            if (!claims.get("type").equals(type)) {
                throw new AuthException("Expected " + type + " token");
            }
        } catch (Exception e) {
            throw new AuthException("Invalid token");
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
            throw new AuthException("Invalid token");
        }
    }

    public TokenInfoDTO getTokenInfo(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            TokenInfoDTO tokenInfo = new TokenInfoDTO();
            tokenInfo.setEmail(claims.getSubject());
            tokenInfo.setExpireTime(claims.getExpiration().getTime());
            return tokenInfo;
        } catch (Exception e) {
            throw new AuthException("Invalid token");
        }
    }
}
