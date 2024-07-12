package cn.d619.poesy.qwen.pojo.dto;

import lombok.Data;

@Data
public class QwenResponse {
    private String response;

    public QwenResponse() {
    }

    public QwenResponse(String response) {
        this.response = response;
    }
}
