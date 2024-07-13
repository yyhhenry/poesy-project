package cn.d619.poesy.articlecomment.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.d619.poesy.articlecomment.mapper.ArticleCommentMapper;
import cn.d619.poesy.articlecomment.pojo.po.ArticleCommentPO;

@Service
public class ArticleCommentService {
    @Autowired
    private ArticleCommentMapper articlecommentMapper;

    public String addArticleComment(String id, String content,
            String authorEmail, String articleId, LocalDateTime createdTime) {
        ArticleCommentPO articlecommentPO = new ArticleCommentPO(id, content, authorEmail, articleId, createdTime);
        articlecommentMapper.insert(articlecommentPO);
        return articlecommentPO.getId();
    }

    public ArticleCommentPO getArticleComment(String id) {
        return articlecommentMapper.selectById(id);
    }

}
