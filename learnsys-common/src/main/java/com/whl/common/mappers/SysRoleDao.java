package com.whl.common.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whl.common.models.SysRoleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

/**
 * 角色
 */
@Mapper
public interface SysRoleDao extends BaseMapper<SysRoleEntity> {

    Set<SysRoleEntity> selectRolesByUserId(Long userId);
}
