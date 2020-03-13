package com.whl.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whl.common.mappers.VideoInfoDao;
import com.whl.common.models.VideoInfoEntity;
import com.whl.common.service.VideoInfoService;
import org.springframework.stereotype.Service;


@Service("videoInfoService")
public class VideoInfoServiceImpl extends ServiceImpl<VideoInfoDao, VideoInfoEntity> implements VideoInfoService {


}
