package poesy.answer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import cn.d619.poesy.answer.pojo.po.AnswerPO;
import cn.d619.poesy.answer.service.AnswerService;
import cn.d619.poesy.answer.AnswerApplication;

@SpringBootTest(classes = { AnswerApplication.class })
public class AnswerServiceTest {

    @Autowired
    private AnswerService answerService;

    @Test
    void testAddQuestion() {
        String email = "test@example.com";

        answerService.addAnswer("questionId", "content", email);
    }

    @Test
    void testGetAnswerByQuestion() {
        // 假设已知一个存在的问题 ID
        String questionId = "1"; // 替换为实际存在的问题 ID

        // 调用 AnswerService 的 getAnswerByQuestion 方法
        AnswerPO answer = answerService.getAnswerByQuestion(questionId);

        // 验证获取操作的正确性
        assertNotNull(answer);
        assertEquals(questionId, answer.getQuestionId()); // 假设 AnswerPO 中有一个 getQuestionId 方法
    }
}
