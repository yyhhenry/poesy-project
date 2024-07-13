package cn.d619.poesy.article.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.d619.poesy.article.pojo.po.ArticlePO;

@Mapper
public interface ArticleMapper extends BaseMapper<ArticlePO> {
    /*
     <mapper namespace="com.example.mapper.ArticleMapper">
        <!-- 查询操作 -->
        <select id="findArticleById" parameterType="cn.d619.poesy.article.pojo.po.ArticlePO" resultType="cn.d619.poesy.article.pojo.po.ArticlePO">
            select * from article where id = #{id};
        </select>
        
        <!-- 插入操作 -->
        <insert id="insert" parameterType="cn.d619.poesy.article.pojo.po.ArticlePO">
            insert into article (id, title, content, author_email) values (#{id}, #{title}, #{content}, #{authorEmail});
        </insert>
    </mapper>
     */
    
    
}
