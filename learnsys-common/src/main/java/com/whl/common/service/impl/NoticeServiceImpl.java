package com.whl.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whl.common.mappers.NoticeDao;
import com.whl.common.models.NoticeEntity;
import com.whl.common.service.NoticeService;
import org.springframework.stereotype.Service;


@Service("noticeService")
public class NoticeServiceImpl extends ServiceImpl<NoticeDao, NoticeEntity> implements NoticeService {


}
