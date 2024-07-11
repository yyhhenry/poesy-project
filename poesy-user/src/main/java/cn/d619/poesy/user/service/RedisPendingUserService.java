package cn.d619.poesy.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import cn.d619.poesy.user.pojo.po.PendingUserPO;

@Service
public class RedisPendingUserService {
    @Autowired
    private RedisTemplate<String, PendingUserPO> redisTemplate;

    private static final String PENDING_USER_PREFIX = "pending-user:";

    public String keyFrom(String email) {
        return PENDING_USER_PREFIX + email;
    }

    public boolean savePendingUser(PendingUserPO pendingUserDTO) {
        String key = keyFrom(pendingUserDTO.getEmail());
        Boolean result = redisTemplate.opsForValue().setIfAbsent(key, pendingUserDTO);
        if (result == null) {
            throw new RuntimeException("Redis error");
        }
        if (result.equals(true)) {
            redisTemplate.expire(key, 5 * 60, java.util.concurrent.TimeUnit.SECONDS);
            return true;
        }
        return false;
    }

    public PendingUserPO getPendingUser(String email) {
        return redisTemplate.opsForValue().get(keyFrom(email));
    }

    public void deletePendingUser(String email) {
        redisTemplate.delete("pending-user:" + email);
    }
}
