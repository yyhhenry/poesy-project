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
    private String authoremail;
    private Data createdtime;

    public QuestionPO(String id, String title, String content, String authoremail, Data createdtime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authoremail = authoremail;
        this.createdtime = createdtime;

    }
}
