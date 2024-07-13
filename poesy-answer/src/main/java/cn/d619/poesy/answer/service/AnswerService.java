package cn.d619.poesy.answer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.d619.poesy.answer.mapper.AnswerMapper;
import cn.d619.poesy.answer.pojo.po.AnswerPO;

@Service
public class AnswerService {
    @Autowired
    private AnswerMapper answerMapper;

    public String addAnswer(String questionId, String content, String authorEmail) {
        AnswerPO answerPO = new AnswerPO(questionId, content, authorEmail);
        answerMapper.insert(answerPO);
        return answerPO.getId();
    }

    public AnswerPO getAnswer(String id) {
        return answerMapper.selectById(id);
    }

    public AnswerPO getAnswerByQuestion(String questionId) {
        QueryWrapper<AnswerPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("question_id", questionId);
        return answerMapper.selectOne(queryWrapper);
    }
}
