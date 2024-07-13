package cn.d619.poesy.answer.pojo.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class AnswerDTO {
    private String content;
    private String authorEmail;
    private LocalDateTime createdTime;

    public AnswerDTO() {
    }

    public AnswerDTO(String content, String authorEmail, LocalDateTime createdTime) {
        this.content = content;
        this.authorEmail = authorEmail;
        this.createdTime = createdTime;
    }

}
