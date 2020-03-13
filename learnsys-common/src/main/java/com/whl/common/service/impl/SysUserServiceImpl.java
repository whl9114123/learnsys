package com.whl.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whl.common.mappers.SysUserDao;
import com.whl.common.models.SysUserEntity;
import com.whl.common.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {
 @Autowired
private SysUserDao sysUserDao;

    @Override
    public List<SysUserEntity> queryList() {
        return sysUserDao.getList();
    }
}
