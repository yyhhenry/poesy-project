package cn.d619.poesy.article.pojo.dto;

import lombok.Data;

import java.util.List;

@Data
public class ListArticleBriefDTO {
    private List<ArticleBriefDTO> questionBriefs;

    public ListArticleBriefDTO(List<ArticleBriefDTO> questionBriefs) {
        this.questionBriefs = questionBriefs;
    }
}
