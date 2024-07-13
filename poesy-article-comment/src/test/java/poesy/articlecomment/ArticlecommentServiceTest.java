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

        articlecommentService.addArticleComment("content", "3454485202@qq.com", "123");

    }

    @Test
    void testGetArticleComment() {
        ArticleCommentPO articlecomment = articlecommentService.getArticleComment("id");
        System.out.println(articlecomment);
    }
}
