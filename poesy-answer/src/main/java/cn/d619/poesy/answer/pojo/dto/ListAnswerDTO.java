package cn.d619.poesy.answer.pojo.dto;

import lombok.Data;

import java.util.List;
import cn.d619.poesy.answer.pojo.dto.AnswerDTO;

@Data
public class ListAnswerDTO {
    private List<AnswerDTO> answers;

    public ListAnswerDTO(List<AnswerDTO> answers) {
        this.answers = answers;
    }

}
