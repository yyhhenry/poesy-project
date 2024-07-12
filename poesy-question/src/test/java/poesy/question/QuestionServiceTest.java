package poesy.question;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.d619.poesy.question.QuestionApplication;
import cn.d619.poesy.question.pojo.po.QuestionPO;
import cn.d619.poesy.question.service.QuestionService;
// import cn.d619.poesy.user.UserApplication;
// import cn.d619.poesy.user.service.UserService;

@SpringBootTest(classes = { QuestionApplication.class })
public class QuestionServiceTest {

    @Autowired
    private QuestionService questionService;

    // @Autowired
    // private UserService userService;

    @Test
    void testAddQuestion() {
        String email = "example@example.com";
        // if (!userService.userExists(email)) {
        // userService.addUser(email, "000000");
        // }
        QuestionPO questionPO = new QuestionPO("0000", "title", "content", email);
        questionService.addQuestion(questionPO);
    }
}
