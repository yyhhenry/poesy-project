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
import cn.d619.poesy.user.pojo.dto.TokenInfoDTO;
import cn.d619.poesy.user.pojo.dto.TokenPair;
import cn.d619.poesy.user.pojo.po.UserVerificationPO;
import cn.d619.poesy.user.pojo.po.UserPO;
import cn.d619.poesy.user.util.JwtUtil;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUserVerificationService redisUserVerificationService;

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

    public TokenInfoDTO getTokenInfo(String token) {
        return jwtUtil.getTokenInfo(token);
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
            throw new AuthException("Email 不能为空");
        }

        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        Pattern pattern = Pattern.compile(emailRegex);

        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new AuthException("Email 格式不正确");
        }
    }

    private void checkPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new AuthException("密码不能为空");
        }

        if (password.length() < 6) {
            throw new AuthException("密码长度不能小于6");
        }

        if (password.length() > 20) {
            throw new AuthException("密码长度不能大于20");
        }
    }

    @Transactional
    public void addUser(String email, String password) {
        checkEmail(email);
        checkPassword(password);
        String encodedPassword = passwordEncoder.encode(password);
        if (userExists(email)) {
            throw new AuthException("用户已存在");
        }
        String code = generateCode();
        UserVerificationPO userVerificationDTO = new UserVerificationPO(email, encodedPassword, code);
        if (redisUserVerificationService.saveUserVerification(userVerificationDTO)) {
            emailService.sendVerificationEmail(email, code);
        } else {
            throw new AuthException("已有进行中的验证");
        }
    }

    public TokenPair generateTokenPair(String email) {
        Long accessExpireTime = jwtUtil.generateExpireTime("access");
        String accessToken = jwtUtil.generateToken(email, "access", accessExpireTime);
        Long refreshExpireTime = jwtUtil.generateExpireTime("refresh");
        String refreshToken = jwtUtil.generateToken(email, "refresh", refreshExpireTime);
        redisRefreshTokenService.addRefreshToken(email, refreshToken);
        TokenPair pair = new TokenPair(accessToken, accessExpireTime, refreshToken);
        return pair;
    }

    public TokenPair refreshToken(String refreshToken) {
        jwtUtil.validateTokenWithType(refreshToken, "refresh");
        String email = jwtUtil.getEmailFromToken(refreshToken);
        redisRefreshTokenService.consumeRefreshToken(refreshToken);
        return generateTokenPair(email);
    }

    public void logout(String refreshToken) {
        jwtUtil.validateTokenWithType(refreshToken, "refresh");
        redisRefreshTokenService.consumeRefreshToken(refreshToken);
    }

    @Transactional
    public void verifyUser(String email, String code) {
        UserVerificationPO userVerificationDTO = redisUserVerificationService.getUserVerification(email);
        if (userVerificationDTO == null) {
            throw new AuthException("不存在待验证用户");
        }
        if (!userVerificationDTO.getCode().equals(code)) {
            throw new AuthException("验证码错误");
        }
        UserPO userPO = new UserPO(userVerificationDTO.getEmail(), userVerificationDTO.getPassword());
        userMapper.insert(userPO);
        redisUserVerificationService.deleteUserVerification(email);
    }

    @Transactional
    public void login(String email, String password) {
        UserPO userPO = getUserByEmail(email);
        if (userPO == null) {
            throw new AuthException("用户不存在");
        }
        if (!passwordEncoder.matches(password, userPO.getPassword())) {
            throw new AuthException("密码错误");
        }
    }

}
