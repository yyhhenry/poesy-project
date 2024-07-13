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

}
