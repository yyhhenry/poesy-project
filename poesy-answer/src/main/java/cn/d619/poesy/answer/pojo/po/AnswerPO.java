package cn.d619.poesy.answer.pojo.po;

import java.time.LocalDateTime;
import java.util.UUID;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("answer")
public class AnswerPO {
    @TableId
    private String id; // 答案的唯一标识符
    private String questionId; // 关联的问题ID
    private String content; // 答案的内容
    private String authorEmail; // 作者的邮箱地址
    @TableField(insertStrategy = FieldStrategy.NEVER)
    private LocalDateTime createdTime; // 创建时间

    public AnswerPO() {
    }

    public AnswerPO(String questionId, String content, String authorEmail) {
        this.id = UUID.randomUUID().toString();
        this.questionId = questionId;
        this.content = content;
        this.authorEmail = authorEmail;
    }
}
