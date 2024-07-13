package cn.d619.poesy.articlecomment.pojo.po;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("article_comment")
public class ArticleCommentPO {
    @TableId
    private String id;
    private String content;
    private String authorEmail;
    private String articleId;
    @TableField(insertStrategy = FieldStrategy.NEVER)
    private LocalDateTime createdTime;

    public ArticleCommentPO() {
    }

    public ArticleCommentPO(String id, String content, String authorEmail, String articleId,
            LocalDateTime createdTime) {
        this.id = id;
        this.content = content;
        this.authorEmail = authorEmail;
        this.articleId = articleId;
        this.createdTime = createdTime;
    }
}
