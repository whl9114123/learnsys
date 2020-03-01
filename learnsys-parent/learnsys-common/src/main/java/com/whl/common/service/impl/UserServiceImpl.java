package com.whl.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whl.common.mappers.UserDao;
import com.whl.common.models.UserEntity;
import com.whl.common.service.UserService;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {


}
