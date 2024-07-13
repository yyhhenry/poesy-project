package cn.d619.poesy.answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import cn.d619.poesy.answer.pojo.po.AnswerPO;
import cn.d619.poesy.answer.service.AnswerService;
import cn.d619.poesy.answer.exception.HttpException;
import cn.d619.poesy.answer.util.JwtUtil;
import cn.d619.poesy.answer.pojo.dto.AddAnswerDTO;
import cn.d619.poesy.answer.pojo.dto.ListAnswerDTO;
import cn.d619.poesy.answer.pojo.dto.uploadDTO;

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

    @PostMapping("/api/question/upload")
    public uploadDTO addAnswer(@RequestBody AddAnswerDTO addAnswerDTO,
            @RequestHeader("Authorization") String auth) {
        if (auth == null || !auth.startsWith("Bearer ")) {
            throw new HttpException(HttpStatus.UNAUTHORIZED, "Missing or invalid token");
        }
        String token = auth.substring(7);
        jwtUtil.validateTokenWithType(token, "access");

        String authorEmail = jwtUtil.getEmailFromToken(token);

        String questionId = addAnswerDTO.getQuestionId();
        String content = addAnswerDTO.getContent();
        return new uploadDTO(answerService.addAnswer(questionId, content, authorEmail));
    }

    @GetMapping("/api/by-question/{id}")
    public AnswerPO getAnswer(@PathVariable("id") String id) {
        return answerService.getAnswer(id);
    }

}
