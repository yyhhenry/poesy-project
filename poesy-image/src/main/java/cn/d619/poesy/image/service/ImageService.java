package cn.d619.poesy.image.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import cn.d619.poesy.image.exception.HttpException;
import cn.d619.poesy.image.mapper.ImageMapper;
import cn.d619.poesy.image.pojo.dto.UploadResponseDTO;
import cn.d619.poesy.image.pojo.po.ImagePO;

@Service
public class ImageService {
    @Autowired
    private ImageMapper imageMapper;

    public void ensureSupportedFileType(String ext) {
        if (!ext.equals(".jpg") && !ext.equals(".jpeg") && !ext.equals(".png")) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Invalid file type, only jpg, jpeg and png are supported");
        }
    }

    public MediaType getMediaType(String ext) {
        switch (ext) {
            case ".jpg":
            case ".jpeg":
                return MediaType.IMAGE_JPEG;
            case ".png":
                return MediaType.IMAGE_PNG;
            default:
                throw new HttpException(HttpStatus.BAD_REQUEST,
                        "Invalid file type, only jpg, jpeg and png are supported");
        }
    }

    public UploadResponseDTO saveImage(String ext, byte[] content) {
        ensureSupportedFileType(ext);
        ImagePO image = new ImagePO(ext, content);
        imageMapper.insert(image);
        String id = image.getId();
        return new UploadResponseDTO(id);
    }

    public ImagePO getImage(String id) {
        return imageMapper.selectById(id);
    }
}
