package com.whl.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whl.common.mappers.SysDeptDao;
import com.whl.common.models.SysDeptEntity;
import com.whl.common.service.SysDeptService;
import org.springframework.stereotype.Service;


@Service("sysDeptService")
public class SysDeptServiceImpl extends ServiceImpl<SysDeptDao, SysDeptEntity> implements SysDeptService {



}
