package cn.d619.poesy.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.nacos.shaded.javax.annotation.Nullable;

@Service
public class RedisUserExistsService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public String keyFrom(String email) {
        return "user-exists:" + email;
    }

    public void cacheNotExistsUser(String email) {
        String key = keyFrom(email);
        redisTemplate.opsForValue().set(key, "not-exists");
        redisTemplate.expire(key, 20, java.util.concurrent.TimeUnit.MINUTES);
    }

    public void cacheUser(String email) {
        String key = keyFrom(email);
        redisTemplate.opsForValue().set(key, "exists");
        redisTemplate.expire(key, 12, java.util.concurrent.TimeUnit.HOURS);
    }

    @Nullable
    public Boolean userExists(String email) {
        String result = redisTemplate.opsForValue().get(keyFrom(email));
        if (result != null) {
            return result.equals("exists");
        }
        return null;
    }
}
