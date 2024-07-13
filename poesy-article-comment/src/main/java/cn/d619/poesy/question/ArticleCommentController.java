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
import cn.d619.poesy.articlecomment.pojo.dto.MsgDTO;
import cn.d619.poesy.articlecomment.pojo.dto.PaginationRequest;
import cn.d619.poesy.articlecomment.pojo.dto.ArticleCommentBriefDTO;
import cn.d619.poesy.articlecomment.pojo.po.ArticleCommentPO;
import cn.d619.poesy.articlecomment.exception.HttpException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleCommentController {
    @Autowired
    private ArticleCommentService articlecommentService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/api/articlecomment/upload")
    public MsgDTO addArticleComment(@RequestBody AddArticleCommentDTO addArticleCommentDTO, @RequestHeader("Authorization") String auth) {
        if (auth == null || !auth.startsWith("Bearer ")) {
            throw new HttpException(HttpStatus.UNAUTHORIZED, "Missing or invalid token");
        }
        String token = auth.substring(7); // remove "Bearer "
        jwtUtil.validateTokenWithType(token, "access");

        String authorEmail = jwtUtil.getEmailFromToken(token);

        String title = addArticleCommentDTO.getTitle();
        String content = addArticleCommentDTO.getContent();
        articlecommentService.addArticleComment(title, content, authorEmail);
        return new MsgDTO("问题上传成功");
    }

    @GetMapping("/api/articlecomment/by/{id}")
    public ArticleCommentBriefDTO[] articlecommentsBy(@RequestBody PaginationRequest paginationRequest) {
        return articlecommentService.articlecommentsBy(paginationRequest);
    }

    @GetMapping("/api/articlecomment/{id}")
    public ArticleCommentPO getArticleComment(@PathVariable("id") String id) {
        return articlecommentService.getArticleComment(id);
    }

    @GetMapping("/api/articlecomment/latest")
    public ArticleCommentBriefDTO[] latestArticleComments(@RequestBody PaginationRequest paginationRequest) {
        return articlecommentService.latestArticleComments(paginationRequest);
    }
}
