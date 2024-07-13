package cn.d619.poesy.answer.pojo.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class AnswerDTO {    
    private String id;
    private String questionId;
    private String content;
    private String authorEmail;
    private LocalDateTime createdTime; 
}
public AnswerDTO() {
}

public AnswerDTO(String id, String questionId, String content, UserDTO authorEmail, LocalDateTime createdTime) {
    this.id = id;
    this.questionId = questionId;
    this.content = content;
    this.authorEmail = authorEmail;
    this.createdTime = createdTime;
}
