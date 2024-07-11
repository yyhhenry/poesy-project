package poesy.user;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import cn.d619.poesy.user.util.JwtUtil;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@SpringBootTest(classes = JwtUtil.class)
public class JwtTest {

    @Test
    void Key() {
        var key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        System.out.println("Key: " + key);
        for (var x : key.getEncoded()) {
            System.out.println(x);
        }
    }
}
