package com.whl.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.whl.common.models.SysMenuEntity;

import java.util.List;
import java.util.Set;

/**
 * 菜单管理
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2020-03-07 18:18:34
 */
public interface SysMenuService extends IService<SysMenuEntity> {



    Set<SysMenuEntity> getPermissionByRoles(List<Long> roleIds);
}

