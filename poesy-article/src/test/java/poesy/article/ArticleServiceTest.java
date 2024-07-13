package poesy.article;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.d619.poesy.article.ArticleApplication;
import cn.d619.poesy.article.pojo.po.ArticlePO;
import cn.d619.poesy.article.service.ArticleService;

@SpringBootTest(classes = { ArticleApplication.class })
public class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    @Test
    void testAddArticle() {
        String email = "test@example.com";
        ArticlePO articlePO = new ArticlePO("title", "content", email);
        articleService.addArticle(email, email, email);
    }

    @Test
    void testSearchArticle() {
        ArticlePO articlePO = new ArticlePO();
        articlePO.setTitle("title");
        articleService.getArticle("id");
    }
}
