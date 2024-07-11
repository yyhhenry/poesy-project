package cn.d619.poesy.user.pojo.dto;

import lombok.Data;

@Data
public class TokenInfoDTO {
    private String email;
    private Long expireTime;
}
