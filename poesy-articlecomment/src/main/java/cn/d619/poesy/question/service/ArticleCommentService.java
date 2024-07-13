package cn.d619.poesy.articlecomment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.d619.poesy.question.mapper.ArticleCommentMapper;
import cn.d619.poesy.question.pojo.po.ArticleCommentPO;

@Service
public class ArticleCommentService {
    @Autowired
    private ArticleCommentMapper articlecommentMapper;

    public void addArticleComment(ArticleCommentPO articlecommentPO) {
        articlecommentMapper.insert(articlecommentPO);
    }
}
