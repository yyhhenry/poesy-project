package cn.d619.poesy.user.pojo.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("user")
public class UserPO {
    @TableId
    private String email;
    private String password;

    public UserPO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
