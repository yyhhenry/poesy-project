package cn.d619.poesy.answer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.d619.poesy.answer.mapper.AnswerMapper;
import cn.d619.poesy.answer.pojo.po.AnswerPO;


@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    @Autowired
    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public Answer addAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    public AnswerPO getAnswer(String id) {
        return AnswerMapper.selectById(id);
    }
}
