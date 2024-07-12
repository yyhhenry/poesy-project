package cn.d619.poesy.question.pojo.po;

import java.time.LocalDateTime;
import java.util.UUID;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("question")
public class QuestionPO {
    @TableId
    private String id;
    private String title;
    private String content;
    private String authorEmail;
    @TableField(insertStrategy = FieldStrategy.NEVER)
    private LocalDateTime createdTime;

    public QuestionPO() {
    }

    public QuestionPO(String title, String content, String authorEmail) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.content = content;
        this.authorEmail = authorEmail;
    }
}
