package cn.d619.poesy.article.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import cn.d619.poesy.article.exception.HttpException;
import cn.d619.poesy.article.mapper.ArticleMapper;
import cn.d619.poesy.article.pojo.dto.PaginationRequest;
import cn.d619.poesy.article.pojo.dto.ArticleBriefDTO;
import cn.d619.poesy.article.pojo.po.ArticlePO;

@Service
public class ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    private void ensureValidPaginationRequest(PaginationRequest paginationRequest) {
        if (paginationRequest.getLimit() <= 0 || paginationRequest.getStart() < 0) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Invalid pagination request");
        }
        if (paginationRequest.getLimit() > 15) {
            throw new IllegalArgumentException("Limit too large");
        }
    }

    public String addArticle(String title, String content, String authorEmail) {
        ArticlePO articlePO = new ArticlePO(title, content, authorEmail);
        articleMapper.insert(articlePO);
        return articlePO.getId();
    }

    public ArticlePO getArticle(String id) {
        return articleMapper.selectById(id);
    }

    public ArticleBriefDTO[] articlesBy(PaginationRequest paginationRequest) {
        ensureValidPaginationRequest(paginationRequest);
        /// TODO: implement this method
        throw new UnsupportedOperationException();
    }

    public ArticleBriefDTO[] latestArticles(PaginationRequest paginationRequest) {
        ensureValidPaginationRequest(paginationRequest);
        /// TODO: implement this method
        throw new UnsupportedOperationException();
    }
}
