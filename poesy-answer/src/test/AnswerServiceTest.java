package cn.d619.poesy.answer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.d619.poesy.answer.service.AnswerService;

@SpringBootTest(classes = { AnswerApplication.class }) // 替换为你的 AnswerApplication 类
public class AnswerServiceTest {

    @Autowired
    private AnswerService answerService;

    @Test
    void testAddAnswer() {
        // 创建一个示例 AnswerPO 对象
        AnswerPO answerPO = new AnswerPO();
        answerPO.setContent("This is an answer content.");

        // 调用 AnswerService 的 addAnswer 方法
        AnswerPO addedAnswer = answerService.addAnswer(answerPO);

        // 可以添加断言来验证添加操作的正确性
        // 例如验证返回的 AnswerPO 是否不为空，是否包含正确的内容
        assertNotNull(addedAnswer);
        assertNotNull(addedAnswer.getId());
        assertEquals("This is an answer content.", addedAnswer.getContent());
    }

    @Test
    void testGetAnswer() {
        // 假设已知一个存在的 AnswerPO 的 id
        String existingAnswerId = "1";

        // 调用 AnswerService 的 getAnswer 方法
        AnswerPO answer = answerService.getAnswer(existingAnswerId);

        // 可以添加断言来验证获取操作的正确性
        // 例如验证返回的 AnswerPO 是否不为空，是否包含正确的 id
        assertNotNull(answer);
        assertEquals(existingAnswerId, answer.getId());
    }
}
