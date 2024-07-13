package cn.d619.poesy.article;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;
import cn.d619.poesy.article.service.ArticleService;
import cn.d619.poesy.article.util.JwtUtil;
import cn.d619.poesy.article.pojo.dto.AddArticleDTO;
import cn.d619.poesy.article.pojo.dto.PaginationRequest;
import cn.d619.poesy.article.pojo.dto.ArticleBriefDTO;
import cn.d619.poesy.article.pojo.dto.UploadDTO;
import cn.d619.poesy.article.pojo.dto.ListArticleBriefDTO;
import cn.d619.poesy.article.exception.HttpException;
import cn.d619.poesy.article.pojo.po.ArticlePO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/api/article/upload")
    public UploadDTO addArticle(@RequestBody AddArticleDTO addArticleDTO, @RequestHeader("Authorization") String auth) {
        if (auth == null || !auth.startsWith("Bearer ")) {
            throw new HttpException(HttpStatus.UNAUTHORIZED, "Missing or invalid token");
        }
        String token = auth.substring(7); // remove "Bearer "
        jwtUtil.validateTokenWithType(token, "access");

        String authorEmail = jwtUtil.getEmailFromToken(token);

        String title = addArticleDTO.getTitle();
        String content = addArticleDTO.getContent();
        String uploadMessage = articleService.addArticle(title, content, authorEmail);
        UploadDTO upload = new UploadDTO(uploadMessage);
        return upload;
    }

    @GetMapping("/api/article/by-user")
    public List<ArticleBriefDTO> articlesBy(@RequestParam("email") String email,
            @RequestHeader("Authorization") String auth) {
        if (auth == null || !auth.startsWith("Bearer ")) {
            throw new HttpException(HttpStatus.UNAUTHORIZED, "Missing or invalid token");
        }
        String token = auth.substring(7); // remove "Bearer "
        jwtUtil.validateTokenWithType(token, "access");

        return articleService.articlesBy(email);

        // throw new UnsupportedOperationException();
    }

    @GetMapping("/api/article/{id}")
    public ArticlePO getArticle(@PathVariable("id") String id) {
        return articleService.getArticle(id);
    }

    @GetMapping("/api/article/latest")
    public ListArticleBriefDTO latestArticles(@RequestParam("offset") String offset) {
        // return articleService.latestArticles(paginationRequest);
        ListArticleBriefDTO listArticleBriefDTO = new ListArticleBriefDTO(
                articleService.latestArticles(new PaginationRequest(Integer.parseInt(offset), 6)));
        // articleService.latestArticles(paginationRequest);
        return listArticleBriefDTO;
    }

}
