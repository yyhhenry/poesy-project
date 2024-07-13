package cn.d619.poesy.answer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.d619.poesy.answer.mapper.AnswerMapper;
import cn.d619.poesy.answer.pojo.dto.AnswerDTO;
import cn.d619.poesy.answer.pojo.dto.ListAnswerDTO;
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

    public ListAnswerDTO getAnswerByQuestion(String questionId) {
        QueryWrapper<AnswerPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "content", "author_email", "created_time");
        queryWrapper.eq("question_id", questionId);
        List<AnswerPO> answers = answerMapper.selectList(queryWrapper);
        List<AnswerDTO> answerDTOs = answers.stream()
                .map(answer -> new AnswerDTO(answer.getId(),
                        answer.getContent(),
                        answer.getAuthorEmail(),
                        answer.getCreatedTime()))
                .toList();
        return new ListAnswerDTO(answerDTOs);
    }
}
