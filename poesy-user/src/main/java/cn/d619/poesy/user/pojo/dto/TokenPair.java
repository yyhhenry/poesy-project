package cn.d619.poesy.user.pojo.dto;

import lombok.Data;

@Data
public class TokenPair {
    private String accessToken;
    private String refreshToken;

    public TokenPair(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
