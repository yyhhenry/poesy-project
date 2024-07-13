package cn.d619.poesy.question.pojo.dto;

import lombok.Data;

import java.util.List;

@Data
public class ListQuestionBriefDTO {
    private List<QuestionBriefDTO> questionBriefs;

    public ListQuestionBriefDTO(List<QuestionBriefDTO> questionBriefs) {
        this.questionBriefs = questionBriefs;
    }
}
