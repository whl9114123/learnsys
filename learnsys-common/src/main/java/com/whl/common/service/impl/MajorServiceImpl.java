package com.whl.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whl.common.mappers.MajorDao;
import com.whl.common.models.MajorEntity;
import com.whl.common.service.MajorService;
import org.springframework.stereotype.Service;


@Service("majorService")
public class MajorServiceImpl extends ServiceImpl<MajorDao, MajorEntity> implements MajorService {


}
