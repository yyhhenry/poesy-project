package cn.d619.poesy.answer.pojo.dto;

import lombok.Data;

import java.util.List;

@Data
public class ListAnswerDTO {
    private List<AnswerDTO> answers;

    public ListAnswerDTO(List<AnswerDTO> answers) {
        this.answers = answers;
    }

}
