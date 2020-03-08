package com.whl.common.mappers.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whl.common.models.SysUserEntity;

import java.util.List;

/**
 * 系统用户
 * 
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2020-03-07 17:34:54
 */

public interface SysUserDao extends BaseMapper<SysUserEntity> {

    List<SysUserEntity> getList();
}
