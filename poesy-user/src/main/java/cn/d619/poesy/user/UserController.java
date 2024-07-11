package cn.d619.poesy.user;

import org.springframework.web.bind.annotation.RestController;

import cn.d619.poesy.user.pojo.dto.MsgDTO;
import cn.d619.poesy.user.pojo.dto.RefreshRequest;
import cn.d619.poesy.user.pojo.dto.RegisterRequest;
import cn.d619.poesy.user.pojo.dto.TokenPair;
import cn.d619.poesy.user.pojo.dto.VerifyRequest;
import cn.d619.poesy.user.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/api/user/register")
    public MsgDTO register(@RequestBody RegisterRequest registerRequest) {
        userService.addUser(registerRequest.getEmail(), registerRequest.getPassword());
        return new MsgDTO("User registered successfully, check your email for verification code");
    }

    @PostMapping("/api/user/verify")
    public TokenPair verify(@RequestBody VerifyRequest verifyRequest) {
        String email = verifyRequest.getEmail();
        userService.verifyUser(email, verifyRequest.getCode());
        return userService.generateTokenPair(email);
    }

    @PostMapping("/api/user/login")
    public TokenPair login(@RequestBody RegisterRequest loginRequest) {
        String email = loginRequest.getEmail();
        userService.verifyUser(email, loginRequest.getPassword());
        return userService.generateTokenPair(email);
    }

    @PostMapping("/api/user/refresh")
    public TokenPair refresh(@RequestBody RefreshRequest refreshRequest) {
        return userService.refreshToken(refreshRequest.getRefreshToken());
    }

}
