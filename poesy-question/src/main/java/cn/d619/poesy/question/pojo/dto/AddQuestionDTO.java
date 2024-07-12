package cn.d619.poesy.question.pojo.dto;

import lombok.Data;

@Data
public class AddQuestionDTO {
    private String id;
    private String title;
    private String content;
    private String authorEmail;

    public AddQuestionDTO(String id, String title, String content, String authorEmail) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorEmail = authorEmail;
    }
}
