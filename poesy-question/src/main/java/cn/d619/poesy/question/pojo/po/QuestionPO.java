package cn.d619.poesy.question.pojo.po;

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
    private Data createdTime;

    public QuestionPO(String id, String title, String content, String authorEmail, Data createdTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorEmail = authorEmail;
        this.createdTime = createdTime;

    }
}
