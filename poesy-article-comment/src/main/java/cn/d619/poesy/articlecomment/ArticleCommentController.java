package cn.d619.poesy.articlecomment;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.http.HttpStatus;
import cn.d619.poesy.articlecomment.service.ArticleCommentService;
import cn.d619.poesy.articlecomment.util.JwtUtil;
import cn.d619.poesy.articlecomment.pojo.dto.AddArticleCommentDTO;
import cn.d619.poesy.articlecomment.pojo.dto.ListCommentDTO;
import cn.d619.poesy.articlecomment.pojo.dto.MsgDTO;
import cn.d619.poesy.articlecomment.exception.HttpException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class ArticleCommentController {
    @Autowired
    private ArticleCommentService articleCommentService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/api/article-comment/upload")
    public MsgDTO addArticleComment(@RequestBody AddArticleCommentDTO addArticleCommentDTO,
            @RequestHeader("Authorization") String auth) {
        if (auth == null || !auth.startsWith("Bearer ")) {
            throw new HttpException(HttpStatus.UNAUTHORIZED, "Missing or invalid token");
        }
        String token = auth.substring(7); // remove "Bearer "
        jwtUtil.validateTokenWithType(token, "access");

        String authorEmail = jwtUtil.getEmailFromToken(token);
        String content = addArticleCommentDTO.getContent();
        String articleId = addArticleCommentDTO.getArticleId();
        articleCommentService.addArticleComment(content, authorEmail, articleId);
        return new MsgDTO("评论上传成功");
    }

    @GetMapping("/api/article-comment/by-article/{id}")
    public ListCommentDTO articleCommentsBy(@PathVariable("id") String id) {
        return new ListCommentDTO(articleCommentService.getCommentsByArticle(id));
    }

}
