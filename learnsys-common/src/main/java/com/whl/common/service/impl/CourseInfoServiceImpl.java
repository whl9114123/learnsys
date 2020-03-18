package com.whl.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whl.common.mappers.CourseInfoDao;
import com.whl.common.models.CourseInfoEntity;
import com.whl.common.service.CourseInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service("courseInfoService")
public class CourseInfoServiceImpl extends ServiceImpl<CourseInfoDao, CourseInfoEntity> implements CourseInfoService {

@Resource
    CourseInfoDao courseInfoDao;
}
