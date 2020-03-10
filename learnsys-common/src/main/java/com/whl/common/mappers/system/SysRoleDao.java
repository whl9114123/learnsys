package com.whl.common.mappers.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whl.common.models.SysRoleEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色
 * 
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2020-03-07 18:18:34
 */
@Mapper
public interface SysRoleDao extends BaseMapper<SysRoleEntity> {
	
}
