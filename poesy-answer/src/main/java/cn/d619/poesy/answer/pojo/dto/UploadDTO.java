package cn.d619.poesy.answer.pojo.dto;

import lombok.Data;

@Data
public class UploadDTO {
    String id;

    public String getId() {
        return id;
    }

    public UploadDTO() {
    }

    public UploadDTO(String id) {
        this.id = id;
    }

}
