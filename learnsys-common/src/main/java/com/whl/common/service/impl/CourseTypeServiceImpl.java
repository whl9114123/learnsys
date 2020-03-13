package com.whl.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whl.common.mappers.CourseTypeDao;
import com.whl.common.models.CourseTypeEntity;
import com.whl.common.service.CourseTypeService;
import org.springframework.stereotype.Service;


@Service("courseTypeService")
public class CourseTypeServiceImpl extends ServiceImpl<CourseTypeDao, CourseTypeEntity> implements CourseTypeService {


}
