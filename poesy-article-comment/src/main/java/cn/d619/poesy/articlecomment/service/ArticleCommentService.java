package cn.d619.poesy.articlecomment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import cn.d619.poesy.articlecomment.mapper.ArticleCommentMapper;
import cn.d619.poesy.articlecomment.pojo.po.ArticleCommentPO;
import cn.d619.poesy.articlecomment.pojo.dto.ArticleCommentDTO;

@Service
public class ArticleCommentService {
    @Autowired
    private ArticleCommentMapper articleCommentMapper;

    public String addArticleComment(String content,
            String authorEmail, String articleId) {
        ArticleCommentPO articleCommentPO = new ArticleCommentPO(content, authorEmail, articleId);
        articleCommentMapper.insert(articleCommentPO);
        return articleCommentPO.getId();
    }

    public ArticleCommentPO getArticleComment(String id) {
        return articleCommentMapper.selectById(id);
    }

    public List<ArticleCommentDTO> getCommentsByArticle(String articleId) {
        QueryWrapper<ArticleCommentPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("article_id", articleId);
        queryWrapper.select("id", "content", "author_email", "created_time");
        queryWrapper.orderByAsc("created_time");
        List<ArticleCommentPO> articleCommentPOList = articleCommentMapper.selectList(queryWrapper);
        return articleCommentPOList.stream()
                .map(articleCommentPO -> new ArticleCommentDTO(
                        articleCommentPO.getId(),
                        articleCommentPO.getContent(),
                        articleCommentPO.getAuthorEmail(),
                        articleCommentPO.getCreatedTime().toString()))
                .toList();
    }

}
