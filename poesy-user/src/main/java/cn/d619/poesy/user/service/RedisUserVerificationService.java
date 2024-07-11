package cn.d619.poesy.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import cn.d619.poesy.user.pojo.po.UserVerificationPO;

@Service
public class RedisUserVerificationService {
    @Autowired
    private RedisTemplate<String, UserVerificationPO> redisTemplate;

    public String keyFrom(String email) {
        return "user-verification:" + email;
    }

    public boolean saveUserVerification(UserVerificationPO userVerificationDTO) {
        String key = keyFrom(userVerificationDTO.getEmail());
        Boolean result = redisTemplate.opsForValue().setIfAbsent(key, userVerificationDTO);
        if (result == null) {
            throw new RuntimeException("Redis error");
        }
        if (result.equals(true)) {
            redisTemplate.expire(key, 2 * 60, java.util.concurrent.TimeUnit.SECONDS);
            return true;
        }
        return false;
    }

    public UserVerificationPO getUserVerification(String email) {
        return redisTemplate.opsForValue().get(keyFrom(email));
    }

    public void deleteUserVerification(String email) {
        redisTemplate.delete(keyFrom(email));
    }
}
