package cn.d619.poesy.article.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

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
        if (paginationRequest.getSize() <= 0 || paginationRequest.getOffset() < 0) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Invalid pagination request");
        }
        if (paginationRequest.getSize() > 15) {
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

    public List<ArticleBriefDTO> articlesBy(String authorEmail) {
        QueryWrapper<ArticlePO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("author_email", authorEmail);
        queryWrapper.select("id", "title", "author_email", "created_time");
        List<ArticlePO> articlePOList = articleMapper.selectList(queryWrapper);
        return articlePOList.stream()
                .map(articlePO -> new ArticleBriefDTO(articlePO.getId(), articlePO.getTitle(),
                        articlePO.getCreatedTime().toString(), articlePO.getAuthorEmail()))
                .toList();
    }

    public List<ArticleBriefDTO> latestArticles(PaginationRequest paginationRequest) {
        ensureValidPaginationRequest(paginationRequest);
        // 创建查询条件
        QueryWrapper<ArticlePO> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("created_time");
        queryWrapper.select("id", "title", "author_email", "created_time");
        // 添加 LIMIT 子句
        queryWrapper.last("LIMIT " + paginationRequest.getOffset() + "," + paginationRequest.getSize());

        List<ArticlePO> articlePOList = articleMapper.selectList(queryWrapper);
        return articlePOList.stream()
                .map(articlePO -> new ArticleBriefDTO(articlePO.getId(), articlePO.getTitle(),
                        articlePO.getCreatedTime().toString(), articlePO.getAuthorEmail()))
                .toList();
    }
}
