package cn.d619.poesy.question.pojo.dto;

import lombok.Data;

@Data
public class QuestionBriefDTO {

    private String id;
    private String title;
    private String authorEmail;
    private String createdTime;

    public QuestionBriefDTO() {

    }

    public QuestionBriefDTO(String id, String title, String createdTime, String authorEmail) {
        this.id = id;
        this.title = title;
        this.createdTime = createdTime;
        this.authorEmail = authorEmail;
    }
}
