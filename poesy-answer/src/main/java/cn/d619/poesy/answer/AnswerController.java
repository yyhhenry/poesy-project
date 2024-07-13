package cn.d619.poesy.answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.d619.poesy.answer.service.AnswerService;
import cn.d619.poesy.answer.exception.HttpException;
import cn.d619.poesy.answer.util.JwtUtil;
import cn.d619.poesy.answer.pojo.dto.AddAnswerDTO;
import cn.d619.poesy.answer.pojo.dto.ListAnswerDTO;
import cn.d619.poesy.answer.pojo.dto.UploadDTO;

@RestController
@RequestMapping("/api/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;
    @Autowired
    private JwtUtil jwtUtil;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping("/upload")
    public UploadDTO addAnswer(@RequestBody AddAnswerDTO addAnswerDTO,
            @RequestHeader("Authorization") String auth) {
        if (auth == null || !auth.startsWith("Bearer ")) {
            throw new HttpException(HttpStatus.UNAUTHORIZED, "Missing or invalid token");
        }
        String token = auth.substring(7);
        jwtUtil.validateTokenWithType(token, "access");

        String authorEmail = jwtUtil.getEmailFromToken(token);

        String questionId = addAnswerDTO.getQuestionId();
        String content = addAnswerDTO.getContent();
        return new UploadDTO(answerService.addAnswer(questionId, content, authorEmail));
    }

    @GetMapping("/by-question/{id}")
    public ListAnswerDTO getAnswer(@PathVariable("id") String id) {
        return answerService.getAnswerByQuestion(id);
    }

}
