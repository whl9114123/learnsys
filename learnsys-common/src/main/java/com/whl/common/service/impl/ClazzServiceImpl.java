package com.whl.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whl.common.mappers.ClazzDao;
import com.whl.common.models.ClazzEntity;
import com.whl.common.service.ClazzService;
import org.springframework.stereotype.Service;


@Service("clazzService")
public class ClazzServiceImpl extends ServiceImpl<ClazzDao, ClazzEntity> implements ClazzService {


}
