package cn.d619.poesy.articlecomment.pojo.dto;

import lombok.Data;

@Data
public class AddArticleCommentDTO {
    private String title;
    private String content;

    public AddArticleCommentDTO() {
    }

    public AddArticleCommentDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
