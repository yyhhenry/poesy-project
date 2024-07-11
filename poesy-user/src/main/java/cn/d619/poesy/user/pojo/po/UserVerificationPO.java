package cn.d619.poesy.user.pojo.po;

import lombok.Data;

@Data
public class UserVerificationPO {
    private String email;
    private String password;
    private String code;

    public UserVerificationPO() {
    }

    public UserVerificationPO(String email, String password, String code) {
        this.email = email;
        this.password = password;
        this.code = code;
    }
}
