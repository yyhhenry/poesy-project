package cn.d619.poesy.image.pojo.dto;

import lombok.Data;

@Data
public class UploadResponseDTO {
    private String id;
    private String url;

    public UploadResponseDTO(String id) {
        this.id = id;
        this.url = "/api/image/" + id;
    }
}
