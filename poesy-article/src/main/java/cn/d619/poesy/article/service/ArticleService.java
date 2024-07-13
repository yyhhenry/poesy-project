package cn.d619.poesy.article.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.d619.poesy.article.mapper.ArticleMapper;
import cn.d619.poesy.article.pojo.po.ArticlePO;

@Service
public class ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    public void addArticle(ArticlePO articlePO) {
        ArticlePO articlePO = new ArticlePO(title, content, authorEmail);
        articleMapper.insert(articlePO);
        return articlePO.getId();
    }  

    public void searchArticle(ArticlePO articlePO) {
        articleMapper.findArticleById(articlePO);
    }
}
