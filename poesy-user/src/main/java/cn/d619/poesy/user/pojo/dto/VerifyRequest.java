package cn.d619.poesy.user.pojo.dto;

import lombok.Data;

@Data
public class VerifyRequest {
    private String email;
    private String code;
}