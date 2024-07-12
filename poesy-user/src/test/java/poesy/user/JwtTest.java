package poesy.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.d619.poesy.user.UserApplication;
import cn.d619.poesy.user.util.JwtUtil;

@SpringBootTest(classes = UserApplication.class)
public class JwtTest {

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    void generateToken() {
        String type = "access";
        Long expireTime = jwtUtil.generateExpireTime(type);
        String token = jwtUtil.generateToken("admin@example.com", type, expireTime);
        System.out.println(token);
    }

    @Test
    void loadContext() {
    }
}
