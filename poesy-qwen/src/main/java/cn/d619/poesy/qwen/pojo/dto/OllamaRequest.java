package cn.d619.poesy.qwen.pojo.dto;

import lombok.Data;

@Data
public class OllamaRequest {
    private String model;
    private String prompt;
    private boolean stream;

    public OllamaRequest() {
    }

    public OllamaRequest(String model, String prompt, boolean stream) {
        this.model = model;
        this.prompt = prompt;
        this.stream = stream;
    }
}
