package com.whl.common.mappers;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whl.common.models.CommentEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 评论
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2020-04-27 19:30:28
 */
@Mapper
public interface CommentDao extends BaseMapper<CommentEntity> {

}
