package poesy.question;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.d619.poesy.question.QuestionApplication;

import cn.d619.poesy.question.service.QuestionService;

@SpringBootTest(classes = { QuestionApplication.class })
public class QuestionServiceTest {

    @Autowired
    private QuestionService questionService;

    @Test
    void testAddQuestion() {
        String email = "test@example.com";

        questionService.addQuestion("title", "content", email);
    }
}
