package cn.d619.poesy.article.pojo.dto;

import lombok.Data;

import java.util.List;

@Data
public class ListArticleBriefDTO {
    private List<ArticleBriefDTO> listQuestionBriefDTO;

    public ListArticleBriefDTO(List<ArticleBriefDTO> listQuestionBriefDTO) {
        this.listQuestionBriefDTO = listQuestionBriefDTO;
    }
}
