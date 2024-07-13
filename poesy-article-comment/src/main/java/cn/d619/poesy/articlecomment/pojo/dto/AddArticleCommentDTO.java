package cn.d619.poesy.articlecomment.pojo.dto;

import lombok.Data;

@Data
public class AddArticleCommentDTO {

    private String content;

    private String articleId;

    public AddArticleCommentDTO() {
    }

    public AddArticleCommentDTO(String content,
            String articleId) {

        this.content = content;

        this.articleId = articleId;

    }

}
