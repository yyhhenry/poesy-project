package cn.d619.poesy.image.pojo.dto;

import lombok.Data;

@Data
public class ErrorDTO {
    private String error;

    public ErrorDTO(String error) {
        this.error = error;
    }
}
