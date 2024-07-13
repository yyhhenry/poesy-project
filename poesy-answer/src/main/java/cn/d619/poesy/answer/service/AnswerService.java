package cn.d619.poesy.answer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.d619.poesy.answer.mapper.AnswerMapper;
import cn.d619.poesy.answer.pojo.po.AnswerPO;

@Service
public class AnswerService {
    @Autowired
    private AnswerMapper answerMapper;

    public AnswerService() {
        // 如果有其他需要注入的依赖，可以在这里进行初始化
    }

    public AnswerPO getAnswer(String id) {
        return answerMapper.selectById(id);
    }

    public AnswerPO addAnswer(AnswerPO answerPO) {
        return answerMapper.insertAnswerPO(answerPO);
    }

    public AnswerPO getAnswerByQuestion(String questionId) {
        return answerMapper.selectByQuestionId(questionId);
    }
}
