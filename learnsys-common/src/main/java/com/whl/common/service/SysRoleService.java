package com.whl.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.whl.common.models.SysRoleEntity;

import java.util.Set;

/**
 * 角色
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2020-03-07 18:18:34
 */
public interface SysRoleService extends IService<SysRoleEntity> {


    Set<SysRoleEntity> getRolesById(Long userId);
}

