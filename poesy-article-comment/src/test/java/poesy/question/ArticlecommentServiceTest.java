package poesy.articlecomment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.d619.poesy.articlecomment.ArticleCommentApplication;

import cn.d619.poesy.articlecomment.service.ArticleCommentService;

@SpringBootTest(classes = { ArticleCommentApplication.class })
public class ArticleCommentServiceTest {

    @Autowired
    private ArticleCommentService articlecommentService;

    @Test
    void testAddArticleComment() {
        String email = "test@example.com";

        articlecommentService.addArticleComment("id", "content", authorEmail, 
        "articleId", "createdTime");

        
    }
}
