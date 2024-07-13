package cn.d619.poesy.answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cn.d619.poesy.answer.pojo.po.AnswerPO;
import cn.d619.poesy.answer.service.AnswerService;
import cn.d619.poesy.answer.exception.HttpException;
import cn.d619.poesy.answer.exception.AuthException;
import cn.d619.poesy.answer.util.JwtUtil;

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

    @PostMapping
    public ResponseEntity<AnswerPO> addAnswer(@RequestBody AnswerPO answerPO,
            @RequestHeader("Authorization") String auth) {
        if (auth == null || !auth.startsWith("Bearer ")) {
            throw new HttpException(HttpStatus.UNAUTHORIZED, "Missing or invalid token");
        }
        String token = auth.substring(7);
        jwtUtil.validateTokenWithType(token, "access");

        String authorEmail = jwtUtil.getEmailFromToken(token);

        AnswerPO addedAnswer = answerService.addAnswer(answerPO);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedAnswer);
    }

    @GetMapping("/by-question/{id}")
    public ResponseEntity<AnswerPO> getAnswerByQuestion(@PathVariable("id") String id) {
        AnswerPO answer = answerService.getAnswerByQuestion(id);
        if (answer == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(answer);
        }
    }

}
