package cn.d619.poesy.article.pojo.dto;

import lombok.Data;

@Data
public class MsgDTO {
    private String msg;

    public MsgDTO(String msg) {
        this.msg = msg;
    }
}