package com.whl.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whl.common.mappers.UserClazzRelationDao;
import com.whl.common.models.UserClazzRelationEntity;
import com.whl.common.service.UserClazzRelationService;
import org.springframework.stereotype.Service;


@Service("userClazzRelationService")
public class UserClazzRelationServiceImpl extends ServiceImpl<UserClazzRelationDao, UserClazzRelationEntity> implements UserClazzRelationService {


}
