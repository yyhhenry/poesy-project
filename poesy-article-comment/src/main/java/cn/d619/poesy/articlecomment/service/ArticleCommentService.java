package cn.d619.poesy.articlecomment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.d619.poesy.articlecomment.mapper.ArticleCommentMapper;
import cn.d619.poesy.articlecomment.pojo.po.ArticleCommentPO;

@Service
public class ArticleCommentService {
    @Autowired
    private ArticleCommentMapper articlecommentMapper;

    public String addArticleComment(String content,
            String authorEmail, String articleId) {
        ArticleCommentPO articlecommentPO = new ArticleCommentPO(content, authorEmail, articleId);
        articlecommentMapper.insert(articlecommentPO);
        return articlecommentPO.getId();
    }

    public ArticleCommentPO getArticleComment(String id) {
        return articlecommentMapper.selectById(id);
    }

    public List<GetArticleCommentByArticleIdDTO> commentsBy(String articleId) {
        // ensureValidPaginationRequest(paginationRequest);
        QueryWrapper<ArticleCommentPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("article_comment", articleId);
        queryWrapper.select("id", "author_email", "created_time");
        List<ArticleCommentPO> articlecommentPOList = questionMapper.selectList(queryWrapper);
        return questionPOList.stream()
                .map(questionPO -> new QuestionBriefDTO(questionPO.getId(),
                        questionPO.getCreatedTime().toString()))
                .toList();

    }

}
