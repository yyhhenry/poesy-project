package cn.d619.poesy.user;

import org.springframework.web.bind.annotation.RestController;

import cn.d619.poesy.user.pojo.dto.MsgDTO;
import cn.d619.poesy.user.pojo.dto.RegisterRequest;
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
    public MsgDTO verify(@RequestBody VerifyRequest verifyRequest) {
        userService.verifyUser(verifyRequest.getEmail(), verifyRequest.getCode());
        return new MsgDTO("User verified successfully");
    }

}
