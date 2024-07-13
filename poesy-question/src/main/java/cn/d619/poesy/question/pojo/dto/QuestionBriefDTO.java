package cn.d619.poesy.question.pojo.dto;

import lombok.Data;

@Data
public class QuestionBriefDTO {

    private String id;
    private String title;

    public QuestionBriefDTO(String id, String title) {
        this.id = id;
        this.title = title;
    }
}
