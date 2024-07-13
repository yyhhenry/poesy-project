package cn.d619.poesy.answer.dto;

import java.time.LocalDateTime;
import cn.d619.poesy.user.dto.UserDTO;
import lombok.Data;

@Data
public class AnswerDTO {    
    private String id;
    private String questionId;
    private String content;
    private UserDTO author;
    private LocalDateTime createdTime;
}
