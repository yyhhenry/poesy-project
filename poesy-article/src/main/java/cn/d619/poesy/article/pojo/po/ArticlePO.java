package cn.d619.poesy.article.pojo.po;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("article")
public class ArticlePO {
    @TableId
    private String id;
    private String title;
    private String content;
    private String authorEmail;
    @TableField(insertStrategy = FieldStrategy.NEVER)
    private LocalDateTime createdTime;

    public ArticlePO() {
    }

    public ArticlePO(String id, String title, String content, String authorEmail) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorEmail = authorEmail;
    }
}
