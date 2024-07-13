package cn.d619.poesy.articlecomment.pojo.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AddArticleCommentDTO {
    private String id;
    private String content;
    private String authorEmail;
    private String articleId;
    private LocalDateTime createdTime;

    public AddArticleCommentDTO() {
    }

    public AddArticleCommentDTO(String id, String content, String authorEmail,
            String articleId, LocalDateTime createdTime) {
        this.id = id;
        this.content = content;
        this.authorEmail = authorEmail;
        this.articleId = articleId;
        this.createdTime = createdTime;
    }
      public GetArticleCommentByArticleIdDTO(String id, String content, String authorEmail,
        LocalDateTime createdTime) {
        this.id = id;
        this.content = content;
        this.authorEmail = authorEmail;
        this.createdTime = createdTime;
    }
}
