package com.whl.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whl.common.mappers.SysRoleDao;
import com.whl.common.models.SysRoleEntity;
import com.whl.common.service.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;


@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRoleEntity> implements SysRoleService {
    @Resource
   private SysRoleDao sysRoleDao;

    @Override
    public Set<SysRoleEntity> getRolesById(Long userId) {
 return sysRoleDao.selectRolesByUserId(userId);

    }
}
