package com.whl.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.whl.common.models.SysUserEntity;

import java.util.List;

/**
 * 系统用户
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2020-03-07 17:34:54
 */
public interface SysUserService extends IService<SysUserEntity> {


    List<Long> queryAllMenuId(Long userId);

    boolean updatePassword(Long userId, String password, String newPassword);
}

