package com.whl.common.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whl.common.models.SysMenuEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

/**
 * 菜单管理
 * 
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2020-03-07 18:18:34
 */
@Mapper
public interface SysMenuDao extends BaseMapper<SysMenuEntity> {

    Set<SysMenuEntity> getPermissionByRoles(List<Long> roleIds);
}
