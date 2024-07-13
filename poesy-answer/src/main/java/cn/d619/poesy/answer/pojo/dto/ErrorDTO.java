package cn.d619.poesy.answer.pojo.dto;

import lombok.Data;

@Data
public class ErrorDTO {
    private String error;

    public ErrorDTO(String error) {
        this.error = error;
    }
}
