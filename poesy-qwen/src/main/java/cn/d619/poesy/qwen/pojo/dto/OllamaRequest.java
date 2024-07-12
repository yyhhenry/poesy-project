package cn.d619.poesy.qwen.pojo.dto;

import lombok.Data;

@Data
public class OllamaRequest {
    private String model;
    private String prompt;
    private boolean stream;

    public OllamaRequest() {
        this.model = "qwen2";
        this.stream = false;
    }

    public OllamaRequest(String prompt) {
        this.model = "qwen2";
        this.prompt = prompt;
        this.stream = false;
    }
}
