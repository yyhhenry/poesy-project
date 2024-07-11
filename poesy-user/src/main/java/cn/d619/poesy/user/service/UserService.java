package cn.d619.poesy.user.service;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.d619.poesy.user.exception.AuthException;
import cn.d619.poesy.user.mapper.UserMapper;
import cn.d619.poesy.user.pojo.dto.TokenPair;
import cn.d619.poesy.user.pojo.po.PendingUserPO;
import cn.d619.poesy.user.pojo.po.UserPO;
import cn.d619.poesy.user.util.JwtUtil;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisPendingUserService redisPendingUserService;

    @Autowired
    private RedisRefreshTokenService redisRefreshTokenService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    private UserPO getUserByEmail(String email) {
        return userMapper.selectById(email);
    }

    public boolean userExists(String email) {
        return getUserByEmail(email) != null;
    }

    private String generateCode() {
        SecureRandom random = new SecureRandom();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }

    @Transactional
    public void addUser(String email, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        if (userExists(email)) {
            throw new AuthException("User already exists");
        }
        String code = generateCode();
        PendingUserPO pendingUserDTO = new PendingUserPO(email, encodedPassword, code);
        if (redisPendingUserService.savePendingUser(pendingUserDTO)) {
            emailService.sendVerificationEmail(email, code);
        } else {
            throw new AuthException("User is already pending");
        }
    }

    public TokenPair generateTokenPair(String email) {
        String accessToken = jwtUtil.generateToken(email, "access");
        String refreshToken = jwtUtil.generateToken(email, "refresh");
        redisRefreshTokenService.addRefreshToken(email, refreshToken);
        TokenPair pair = new TokenPair(accessToken, refreshToken);
        return pair;
    }

    public TokenPair refreshToken(String refreshToken) {
        jwtUtil.validateTokenWithType(refreshToken, "refresh");
        String email = jwtUtil.getEmailFromToken(refreshToken);
        redisRefreshTokenService.consumeRefreshToken(refreshToken);
        return generateTokenPair(email);
    }

    @Transactional
    public void verifyUser(String email, String code) {
        PendingUserPO pendingUserDTO = redisPendingUserService.getPendingUser(email);
        if (pendingUserDTO == null) {
            throw new AuthException("User is not pending");
        }
        if (!pendingUserDTO.getCode().equals(code)) {
            throw new AuthException("Verification code is incorrect");
        }
        UserPO userPO = new UserPO(pendingUserDTO.getEmail(), pendingUserDTO.getPassword());
        userMapper.insert(userPO);
        redisPendingUserService.deletePendingUser(email);
    }

    @Transactional
    public void login(String email, String password) {
        UserPO userPO = getUserByEmail(email);
        if (userPO == null) {
            throw new AuthException("User does not exist");
        }
        if (!passwordEncoder.matches(password, userPO.getPassword())) {
            throw new AuthException("Password is incorrect");
        }
    }

}
