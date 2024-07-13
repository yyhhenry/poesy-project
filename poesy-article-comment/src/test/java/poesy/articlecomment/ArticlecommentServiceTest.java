package poesy.articlecomment;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.d619.poesy.articlecomment.ArticleCommentApplication;
import cn.d619.poesy.articlecomment.pojo.po.ArticleCommentPO;
import cn.d619.poesy.articlecomment.service.ArticleCommentService;

@SpringBootTest(classes = { ArticleCommentApplication.class })
public class ArticlecommentServiceTest {

    @Autowired
    private ArticleCommentService articlecommentService;

    @Test
    void testAddArticleComment() {
        String email = "test@example.com";
        LocalDateTime timestamp = LocalDateTime.now();
        articlecommentService.addArticleComment("content", email, "articleId");

    }

    @Test
    void testGetArticleComment() {
        ArticleCommentPO articlecommentPO = new ArticleCommentPO();

        articlecommentService.getArticleComment("id");
    }
}
