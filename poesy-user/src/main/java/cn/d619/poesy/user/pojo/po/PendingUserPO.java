package cn.d619.poesy.user.pojo.po;

import lombok.Data;

@Data
public class PendingUserPO {
    private String email;
    private String password;
    private String code;

    public PendingUserPO(String email, String password, String code) {
        this.email = email;
        this.password = password;
        this.code = code;
    }
}
