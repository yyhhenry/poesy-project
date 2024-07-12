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
    private String author_email;
    private Date created_time;

    public QuestionPO(String id, String title, String content, String author_email, Date created_time) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author_email = author_email;
        this.created_time = created_time;

    }
}
