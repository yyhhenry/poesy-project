package cn.d619.poesy.answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cn.d619.poesy.answer.pojo.po.AnswerPO;
import cn.d619.poesy.answer.service.AnswerService;

@RestController
@RequestMapping("/api/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping
    public ResponseEntity<AnswerPO> addAnswer(@RequestBody AnswerPO answerPO) {
        AnswerPO addedAnswer = answerService.addAnswer(answerPO);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedAnswer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnswerPO> findAnswerById(@PathVariable String id) {
        AnswerPO answer = answerService.getAnswer(id);
        if (answer == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(answer);
        }
    }
}
