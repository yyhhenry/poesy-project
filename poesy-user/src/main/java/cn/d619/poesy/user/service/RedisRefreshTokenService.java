package cn.d619.poesy.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import cn.d619.poesy.user.exception.AuthException;

@Service
public class RedisRefreshTokenService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    public String keyFrom(String refreshToken) {
        return "refresh-token:" + refreshToken;
    }

    public void addRefreshToken(String email, String refreshToken) {
        String key = keyFrom(refreshToken);
        redisTemplate.opsForValue().set(key, email);
        redisTemplate.expire(key, 15, java.util.concurrent.TimeUnit.DAYS);
    }

    public void consumeRefreshToken(String refreshToken) {
        String key = keyFrom(refreshToken);
        if (!redisTemplate.delete(key)) {
            throw new AuthException("Refresh token not found");
        }
    }

}
