package com.whl.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whl.common.mappers.SysMenuDao;
import com.whl.common.models.SysMenuEntity;
import com.whl.common.service.SysMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;


@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenuEntity> implements SysMenuService {

    @Resource
    SysMenuDao sysMenuDao;


    @Override
    public Set<SysMenuEntity> getPermissionByRoles(List<Long> roleIds) {
        return sysMenuDao.getPermissionByRoles(roleIds);
    }
}
