package cn.d619.poesy.answer.dto;

import java.time.LocalDateTime;
import cn.d619.poesy.user.dto.UserDTO;
import lombok.Data;

@Data
public class AnswerDTO {
    private Long id;
    private Long questionId;
    private String text;
    private UserDTO author;
}
