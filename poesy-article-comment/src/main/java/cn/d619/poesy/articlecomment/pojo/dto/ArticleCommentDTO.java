package cn.d619.poesy.articlecomment.pojo.dto;

import lombok.Data;

@Data
public class ArticleCommentDTO {
    private String id;
    private String content;
    private String authorEmail;
    private String createdTime;

    public ArticleCommentDTO() {
    }

    public ArticleCommentDTO(String id, String content, String authorEmail,
            String createdTime) {
        this.id = id;
        this.content = content;
        this.authorEmail = authorEmail;
        this.createdTime = createdTime;
    }

}
