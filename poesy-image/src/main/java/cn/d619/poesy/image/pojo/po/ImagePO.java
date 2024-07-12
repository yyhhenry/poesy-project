package cn.d619.poesy.image.pojo.po;

import java.util.UUID;

import lombok.Data;

@Data
public class ImagePO {
    private String id;
    private String ext;
    private byte[] content;

    public ImagePO(String ext, byte[] content) {
        this.id = UUID.randomUUID().toString();
        this.ext = ext;
        this.content = content;
    }
}
