package cn.d619.poesy.question;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;
import cn.d619.poesy.question.service.QuestionService;
import cn.d619.poesy.question.pojo.dto.AddQuestionDTO;
import cn.d619.poesy.question.pojo.dto.MsgDTO;

@RestController
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @PostMapping("/api/question/addquestion")
    public MsgDTO addQuestion(@RequestBody AddQuestionDTO addQuestionDTO) {
        String uuid = UUID.randomUUID().toString();
        String title = addQuestionDTO.getTitle();
        String content = addQuestionDTO.getContent();
        String authorEmail = addQuestionDTO.getAuthorEmail();
        QuestionService.addQuestion(new AddQuestionDTO(uuid, title, content, authorEmail));
        return new MsgDTO("success");
    }
}
