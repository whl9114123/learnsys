package com.whl.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.whl.common.models.SysUserEntity;
import com.whl.common.util.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2020-03-07 17:34:54
 */
public interface SysUserService extends IService<SysUserEntity> {
    PageUtils queryPage(Map<String, Object> params);
    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long userId);

    /**
     * 保存用户
     */
    void saveUser(SysUserEntity user);

    /**
     * 修改用户
     */
    void update(SysUserEntity user);

    /**
     * 修改密码
     *
     * @param userId      用户ID
     * @param password    原密码
     * @param newPassword 新密码
     */
    boolean updatePassword(Long userId, String password, String newPassword);

}

