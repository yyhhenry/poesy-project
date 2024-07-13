package cn.d619.poesy.articlecomment.pojo.dto;

import java.util.List;

import lombok.Data;

@Data
public class ListCommentDTO {
    private List<ArticleCommentDTO> comments;

    public ListCommentDTO() {
    }

    public ListCommentDTO(List<ArticleCommentDTO> comments) {
        this.comments = comments;
    }
}
