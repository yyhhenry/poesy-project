package cn.d619.poesy.question.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.d619.poesy.question.exception.HttpException;
import cn.d619.poesy.question.mapper.QuestionMapper;
import cn.d619.poesy.question.pojo.dto.PaginationRequest;
import cn.d619.poesy.question.pojo.dto.QuestionBriefDTO;
import cn.d619.poesy.question.pojo.po.QuestionPO;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    // @Autowired
    // private QuestionService questionService;

    private void ensureValidPaginationRequest(PaginationRequest paginationRequest) {
        if (paginationRequest.getSize() <= 0 || paginationRequest.getOffset() < 0) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Invalid pagination request");
        }
        if (paginationRequest.getSize() > 15) {
            throw new IllegalArgumentException("Limit too large");
        }
    }

    public String addQuestion(String title, String content, String authorEmail) {
        QuestionPO questionPO = new QuestionPO(title, content, authorEmail);
        questionMapper.insert(questionPO);
        return questionPO.getId();
    }

    public QuestionPO getQuestion(String id) {
        return questionMapper.selectById(id);
    }

    public List<QuestionBriefDTO> questionsBy(String authorEmail) {
        // ensureValidPaginationRequest(paginationRequest);
        QueryWrapper<QuestionPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("author_email", authorEmail);
        queryWrapper.select("id", "title", "author_email", "created_time");
        List<QuestionPO> questionPOList = questionMapper.selectList(queryWrapper);
        return questionPOList.stream()
                .map(questionPO -> new QuestionBriefDTO(questionPO.getId(), questionPO.getTitle(),
                        questionPO.getCreatedTime().toString(), questionPO.getAuthorEmail()))
                .toList();

    }

    public List<QuestionBriefDTO> latestQuestions(PaginationRequest paginationRequest) {
        ensureValidPaginationRequest(paginationRequest);
        // 创建查询条件
        QueryWrapper<QuestionPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("created_time");
        queryWrapper.select("id", "title", "author_email", "created_time");
        // 添加 LIMIT 子句
        queryWrapper.last("LIMIT " + paginationRequest.getOffset() + "," + paginationRequest.getSize());

        List<QuestionPO> questionPOList = questionMapper.selectList(queryWrapper);
        return questionPOList.stream()
                .map(questionPO -> new QuestionBriefDTO(questionPO.getId(), questionPO.getTitle(),
                        questionPO.getCreatedTime().toString(), questionPO.getAuthorEmail()))
                .toList();
        // throw new UnsupportedOperationException();
    }

}
