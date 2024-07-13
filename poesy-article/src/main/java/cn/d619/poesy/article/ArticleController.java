package cn.d619.poesy.article;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.http.HttpStatus;
import cn.d619.poesy.article.service.ArticleService;
import cn.d619.poesy.article.util.JwtUtil;
import cn.d619.poesy.article.pojo.dto.AddArticleDTO;
import cn.d619.poesy.article.pojo.dto.MsgDTO;
import cn.d619.poesy.article.pojo.dto.PaginationRequest;
import cn.d619.poesy.article.pojo.dto.ArticleBriefDTO;
import cn.d619.poesy.article.pojo.po.ArticlePO;
import cn.d619.poesy.article.exception.HttpException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/api/article/upload")
    public MsgDTO addArticle(@RequestBody AddArticleDTO addArticleDTO, @RequestHeader("Authorization") String auth) {
        if (auth == null || !auth.startsWith("Bearer ")) {
            throw new HttpException(HttpStatus.UNAUTHORIZED, "Missing or invalid token");
        }
        String token = auth.substring(7); // remove "Bearer "
        jwtUtil.validateTokenWithType(token, "access");

        String authorEmail = jwtUtil.getEmailFromToken(token);

        String title = addArticleDTO.getTitle();
        String content = addArticleDTO.getContent();
        articleService.addArticle(title, content, authorEmail);
        return new MsgDTO("文章上传成功");
    }

    @GetMapping("/api/article/search")
    public MsgDTO searchArticle(@RequestParam("title") String title) {
        articleService.searchArticle(title);
        return new MsgDTO(true, "文章搜索成功");
    }
    
}
