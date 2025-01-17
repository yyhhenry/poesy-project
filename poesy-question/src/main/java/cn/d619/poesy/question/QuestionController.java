package cn.d619.poesy.question;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;
import cn.d619.poesy.question.service.QuestionService;
import cn.d619.poesy.question.util.JwtUtil;
import cn.d619.poesy.question.pojo.dto.AddQuestionDTO;
import cn.d619.poesy.question.pojo.dto.ListQuestionBriefDTO;

import cn.d619.poesy.question.pojo.dto.PaginationRequest;
import cn.d619.poesy.question.pojo.dto.QuestionBriefDTO;
import cn.d619.poesy.question.pojo.po.QuestionPO;
import cn.d619.poesy.question.exception.HttpException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cn.d619.poesy.question.pojo.dto.UploadDTO;

@RestController
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/api/question/upload")
    public UploadDTO addQuestion(@RequestBody AddQuestionDTO addQuestionDTO,
            @RequestHeader("Authorization") String auth) {
        if (auth == null || !auth.startsWith("Bearer ")) {
            throw new HttpException(HttpStatus.UNAUTHORIZED, "Missing or invalid token");
        }
        String token = auth.substring(7); // remove "Bearer "
        jwtUtil.validateTokenWithType(token, "access");

        String authorEmail = jwtUtil.getEmailFromToken(token);

        String title = addQuestionDTO.getTitle();
        String content = addQuestionDTO.getContent();
        return new UploadDTO(questionService.addQuestion(title, content, authorEmail));
    }

    @GetMapping("/api/question/by-user")
    public ListQuestionBriefDTO questionsBy(@RequestParam("email") String email) {
        return new ListQuestionBriefDTO(questionService.questionsBy(email));
    }

    @GetMapping("/api/question/{id}")
    public QuestionPO getQuestion(@PathVariable("id") String id) {
        return questionService.getQuestion(id);
    }

    @GetMapping("/api/question/latest")
    public ListQuestionBriefDTO latestQuestions(@RequestParam("offset") Long offset) {
        PaginationRequest paginationRequest = new PaginationRequest(offset);
        List<QuestionBriefDTO> briefs = questionService.latestQuestions(paginationRequest);
        return new ListQuestionBriefDTO(briefs);
    }
}
