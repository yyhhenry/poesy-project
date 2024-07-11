package cn.d619.poesy.user.pojo.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
}
