package cn.d619.poesy.question.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.d619.poesy.question.mapper.QuestionMapper;
import cn.d619.poesy.question.pojo.dto.QuestionBriefDTO;
import cn.d619.poesy.question.pojo.po.QuestionPO;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    public void addQuestion(String title, String content, String authorEmail) {
        QuestionPO questionPO = new QuestionPO(title, content, authorEmail);
        questionMapper.insert(questionPO);
    }

    public QuestionPO getQuestion(String id) {
        return questionMapper.selectById(id);
    }

    public QuestionBriefDTO[] latestQuestions() {
        /// TODO: implement this method
        throw new UnsupportedOperationException();
    }

}
