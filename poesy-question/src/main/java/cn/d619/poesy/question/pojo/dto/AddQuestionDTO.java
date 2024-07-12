package cn.d619.poesy.question.pojo.dto;

import lombok.Data;

@Data
public class AddQuestionDTO {
    private String title;
    private String content;

    public AddQuestionDTO() {
    }

    public AddQuestionDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
