package cn.d619.poesy.user;

import org.springframework.web.bind.annotation.RestController;

import cn.d619.poesy.user.pojo.dto.MsgDTO;
import cn.d619.poesy.user.pojo.dto.RefreshRequest;
import cn.d619.poesy.user.pojo.dto.RegisterRequest;
import cn.d619.poesy.user.pojo.dto.TokenInfoDTO;
import cn.d619.poesy.user.pojo.dto.TokenPair;
import cn.d619.poesy.user.pojo.dto.UserExistsDTO;
import cn.d619.poesy.user.pojo.dto.UserExistsRequest;
import cn.d619.poesy.user.pojo.dto.VerifyRequest;
import cn.d619.poesy.user.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/api/user/info")
    public TokenInfoDTO getTokenInfo(@RequestHeader("Authorization") String Auth) {
        String token = Auth.substring(7); // remove "Bearer "
        return userService.getTokenInfo(token);
    }

    @PostMapping("/api/user/exists")
    public UserExistsDTO userExists(@RequestBody UserExistsRequest userExistsRequest) {
        return new UserExistsDTO(userService.userExists(userExistsRequest.getEmail()));
    }

    @PostMapping("/api/user/register")
    public MsgDTO register(@RequestBody RegisterRequest registerRequest) {
        userService.addUser(registerRequest.getEmail(), registerRequest.getPassword());
        return new MsgDTO("注册成功，请检查邮箱并在120秒内验证");
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
