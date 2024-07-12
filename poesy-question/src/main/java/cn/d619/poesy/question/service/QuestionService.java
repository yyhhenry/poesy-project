package cn.d619.poesy.question.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.d619.poesy.question.mapper.QuestionMapper;
import cn.d619.poesy.question.pojo.po.QuestionPO;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    public void addQuestion(QuestionPO questionPO) {
        questionMapper.insert(questionPO);
    }
}