package cn.d619.poesy.question.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import cn.d619.poesy.question.exception.HttpException;
import cn.d619.poesy.question.mapper.QuestionMapper;
import cn.d619.poesy.question.pojo.dto.PaginationRequest;
import cn.d619.poesy.question.pojo.dto.QuestionBriefDTO;
import cn.d619.poesy.question.pojo.po.QuestionPO;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    private void ensureValidPaginationRequest(PaginationRequest paginationRequest) {
        if (paginationRequest.getSize() <= 0 || paginationRequest.getPage() < 0) {
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

    public QuestionBriefDTO[] questionsBy(PaginationRequest paginationRequest, String authorEmail) {
        ensureValidPaginationRequest(paginationRequest);
        /// TODO: implement this method

        throw new UnsupportedOperationException();
    }

    public QuestionBriefDTO[] latestQuestions(PaginationRequest paginationRequest) {
        ensureValidPaginationRequest(paginationRequest);
        /// TODO: implement this method
        throw new UnsupportedOperationException();
    }

}
