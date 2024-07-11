package cn.d619.poesy.user.pojo.dto;

import lombok.Data;

@Data
public class TokenPair {
    private String accessToken;
    private Long expireTime;
    private String refreshToken;

    public TokenPair(String accessToken, Long expireTime, String refreshToken) {
        this.accessToken = accessToken;
        this.expireTime = expireTime;
        this.refreshToken = refreshToken;
    }
}
