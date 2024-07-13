package cn.d619.poesy.answer.pojo.dto;

import lombok.Data;

@Data
public class AddAnswerDTO {
    private String questionId;
    private String content;

    public AddAnswerDTO() {
    }

    public AddAnswerDTO(String questionId, String content) {
        this.questionId = questionId;
        this.content = content;
    }
}
