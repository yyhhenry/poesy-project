package cn.d619.poesy.image;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.d619.poesy.image.exception.HttpException;
import cn.d619.poesy.image.pojo.dto.UploadResponseDTO;
import cn.d619.poesy.image.pojo.po.ImagePO;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cn.d619.poesy.image.service.ImageService;
import cn.d619.poesy.image.util.JwtUtil;

@RestController
public class ImageController {

    @Autowired
    private ImageService ImageService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/api/image/upload")
    public UploadResponseDTO uploadImage(@RequestParam("image") MultipartFile file,
            @RequestHeader("Authorization") String auth) {
        if (auth == null || !auth.startsWith("Bearer ")) {
            throw new HttpException(HttpStatus.UNAUTHORIZED, "Missing or invalid token");
        }
        String token = auth.substring(7); // remove "Bearer "
        jwtUtil.validateTokenWithType(token, "access");

        if (file.isEmpty()) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Empty file");
        }
        String filename = file.getOriginalFilename();
        if (filename == null) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Invalid file name");
        }

        String ext = filename.substring(filename.lastIndexOf('.'));
        byte[] content;
        try {
            content = file.getBytes();
        } catch (Exception e) {
            throw new HttpException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to upload image");
        }
        return ImageService.saveImage(ext, content);
    }

    @GetMapping("/api/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") String id) {
        ImagePO image = ImageService.getImage(id);
        if (image == null) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Image not found");
        }
        byte[] content = image.getContent();
        MediaType mediaType = ImageService.getMediaType(image.getExt());
        return ResponseEntity.ok().contentType(mediaType).body(content);
    }

}
