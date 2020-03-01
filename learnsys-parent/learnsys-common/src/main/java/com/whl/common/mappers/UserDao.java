package com.whl.common.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whl.common.models.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2020-03-01 20:29:16
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
	
}
