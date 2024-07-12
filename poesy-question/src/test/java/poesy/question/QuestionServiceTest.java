package poesy.question;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.d619.poesy.question.QuestionApplication;
import cn.d619.poesy.question.pojo.po.QuestionPO;
import cn.d619.poesy.question.service.QuestionService;

@SpringBootTest(classes = QuestionApplication.class)
public class QuestionServiceTest {

    @Autowired
    private QuestionService questionService;

    @Test
    void testAddQuestion() {
        QuestionPO questionPO = new QuestionPO();
        questionPO.setId("0000");
        questionPO.setTitle("title");
        questionPO.setContent("content");
        questionPO.setAuthorEmail("authorEmail");
        questionService.addQuestion(questionPO);
    }
}
