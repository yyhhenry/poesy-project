package cn.d619.poesy.question.pojo.dto;

import lombok.Data;

import java.util.List;

@Data
public class ListQuestionBriefDTO {
    private List<QuestionBriefDTO> listQuestionBriefDTO;

    public ListQuestionBriefDTO(List<QuestionBriefDTO> listQuestionBriefDTO) {
        this.listQuestionBriefDTO = listQuestionBriefDTO;
    }
}
