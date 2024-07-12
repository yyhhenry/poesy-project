package cn.d619.poesy.question.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.d619.poesy.question.pojo.po.QuestionPO;

@Mapper
public interface QuestionMapper extends BaseMapper<QuestionPO> {
}
