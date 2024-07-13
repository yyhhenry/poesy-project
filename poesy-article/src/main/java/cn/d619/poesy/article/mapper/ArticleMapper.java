package cn.d619.poesy.article.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.d619.poesy.article.pojo.po.ArticlePO;

@Mapper
public interface ArticleMapper extends BaseMapper<ArticlePO> {
}
