/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.whl.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whl.common.mappers.SysRoleDeptDao;
import com.whl.common.models.SysRoleDeptEntity;
import com.whl.common.service.SysRoleDeptService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;


/**
 * 角色与部门对应关系
 *
 * @author Mark sunlightcs@gmail.com
 */
@Service("sysRoleDeptService")
public class SysRoleDeptServiceImpl extends ServiceImpl<SysRoleDeptDao, SysRoleDeptEntity> implements SysRoleDeptService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(Long roleId, List<Long> deptIdList) {
        //先删除角色与部门关系
        deleteBatch(new Long[]{roleId});

        if (CollectionUtils.isEmpty(deptIdList)) {
            return;
        }

        //保存角色与菜单关系
        for (Long deptId : deptIdList) {
            SysRoleDeptEntity sysRoleDeptEntity = new SysRoleDeptEntity();
            sysRoleDeptEntity.setDeptId(deptId);
            sysRoleDeptEntity.setRoleId(roleId);

            this.save(sysRoleDeptEntity);
        }
    }

    @Override
    public List<Long> queryDeptIdList(Long[] roleIds) {
        return baseMapper.queryDeptIdList(roleIds);
    }

    @Override
    public int deleteBatch(Long[] roleIds) {
        return baseMapper.deleteBatch(roleIds);
    }
}
