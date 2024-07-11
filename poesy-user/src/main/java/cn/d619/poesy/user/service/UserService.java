package cn.d619.poesy.user.service;

import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private void checkEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new AuthException("Email is required");
        }

        // 定义邮箱格式的正则表达式
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        // 编译正则表达式
        Pattern pattern = Pattern.compile(emailRegex);

        // 使用正则表达式匹配字符串
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new AuthException("Email format is incorrect");
        }
    }

    private void checkPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new AuthException("Password is required");
        }

        if (password.length() < 6) {
            throw new AuthException("Password must be at least 6 characters long");
        }

        if (password.length() > 20) {
            throw new AuthException("Password must be at most 20 characters long");
        }
    }

    @Transactional
    public void addUser(String email, String password) {
        checkEmail(email);
        checkPassword(password);
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
