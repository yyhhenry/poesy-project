package cn.d619.poesy.question.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.d619.poesy.question.mapper.QuestionMapper;
import cn.d619.poesy.question.pojo.po.QuestionPO;
import cn.d619.poesy.question.pojo.dto.AddQuestionDTO;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    // private QuestionPO getQuestionByTitle(String title) {
    // return QuestionMapper.
    // }
    public void addQuestion(String title, String content, String authorEmail) {
        QuestionPO questionPO = new QuestionPO(title, content, authorEmail);
        questionMapper.insert(questionPO);
    }
}
