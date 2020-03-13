package com.whl.common.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whl.common.models.SysDeptEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 部门管理
 * 
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2020-03-07 18:19:07
 */
@Mapper
public interface SysDeptDao extends BaseMapper<SysDeptEntity> {
	
}
