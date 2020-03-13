package com.whl.common.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whl.common.models.SysMenuEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 菜单管理
 * 
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2020-03-07 18:18:34
 */
@Mapper
public interface SysMenuDao extends BaseMapper<SysMenuEntity> {
	
}
