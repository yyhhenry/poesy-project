package cn.d619.poesy.article.pojo.dto;

import lombok.Data;

@Data
public class AddArticleDTO {
    private String title;
    private String content;

    public AddArticleDTO() {
    }

    public AddArticleDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
