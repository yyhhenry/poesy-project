package cn.d619.poesy.article.pojo.dto;

import lombok.Data;

@Data
public class ArticleBriefDTO {

    private String id;
    private String title;
    private String authorEmail;
    private String createdTime;

    public ArticleBriefDTO() {

    }

    public ArticleBriefDTO(String id, String title, String createdTime, String authorEmail) {
        this.id = id;
        this.title = title;
        this.createdTime = createdTime;
        this.authorEmail = authorEmail;
    }
}
