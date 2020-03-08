package com.whl.learnsys.cms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.whl.common.enums.ResultCode;
import com.whl.common.models.ResultModel;
import com.whl.common.models.SysUserEntity;
import com.whl.common.param.PageParam;
import com.whl.common.service.SysUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api(tags = {"用户模块"})
@RestController
@RequestMapping("sys/user")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @PostMapping( "/list")
    public ResultModel<Page<SysUserEntity>> list(@RequestBody PageParam pageParam) {

        Page<SysUserEntity> page=new Page<>(pageParam.getCurrent(),pageParam.getSize());
        Page<SysUserEntity> page1 =sysUserService.page(page);
        int hasMore=0;
        if(page1.getPages()>=pageParam.getCurrent()) {
            hasMore=1;
        }


        return ResultModel.valueOf(ResultCode.FAILURE,page1,null,hasMore);
    }


    @PostMapping( "/list2")
    public ResultModel<List<SysUserEntity>> list2() {
        List<SysUserEntity> list=  sysUserService.queryList();




        return ResultModel.valueOf(ResultCode.FAILURE,list,null,0);
    }


}
