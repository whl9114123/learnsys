package com.whl.common.mappers;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whl.common.models.UserClazzRelationEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户班级关联表
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2020-04-27 19:30:28
 */
@Mapper
public interface UserClazzRelationDao extends BaseMapper<UserClazzRelationEntity> {

}
