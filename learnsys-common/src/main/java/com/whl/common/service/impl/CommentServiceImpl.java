package com.whl.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whl.common.mappers.CommentDao;
import com.whl.common.models.CommentEntity;
import com.whl.common.service.CommentService;
import org.springframework.stereotype.Service;


@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentDao, CommentEntity> implements CommentService {


}
