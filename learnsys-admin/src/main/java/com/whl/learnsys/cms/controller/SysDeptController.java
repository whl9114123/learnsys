package com.whl.learnsys.cms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.whl.common.enums.ResultCode;
import com.whl.common.models.ResultModel;
import com.whl.common.models.SysDeptEntity;
import com.whl.common.models.SysUserEntity;
import com.whl.common.param.PageParam;
import com.whl.common.param.UserParam;
import com.whl.common.service.SysDeptService;
import com.whl.common.util.DozerUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(tags = "部门管理")
@RestController
@RequestMapping("sys/dept")
public class SysDeptController {
    @Autowired
    private SysDeptService sysDeptService;


    @ApiOperation(value = "获取用户列表", notes = "获取用户列表")
    @ApiImplicitParam(name = "pageParam", value = "获取推荐新闻参数", required = true, dataType = "PageParam")
    @PostMapping( "/list")
    public ResultModel<Page<SysDeptEntity>> list(@RequestBody PageParam pageParam) {

        Page<SysDeptEntity> page=new Page<>(pageParam.getCurrent(),pageParam.getSize());
        Page<SysDeptEntity> page1 =sysDeptService.page(page);
        int hasMore=0;
        if(page1.getPages()>=pageParam.getCurrent()) {
            hasMore=1;
        }


        return ResultModel.valueOf(ResultCode.SUCCESS,page1,null,hasMore);
    }

    @ApiOperation(value = "获取部门详情", notes = "获取部门详情")
    @ApiImplicitParam(name = "id", value = " 参数", required = true, dataType = "Long")
    @GetMapping( "/{id}")
    public ResultModel<SysDeptEntity> getDetail( @PathVariable("id") Long id) {

        SysDeptEntity dept = sysDeptService.getById(id);


        return ResultModel.valueOf(ResultCode.SUCCESS,dept);
    }

    @ApiOperation(value = "删除一个部门", notes = "删除一个部门")
    @ApiImplicitParam(name = "id", value = " 参数", required = true, dataType = "Long")
    @DeleteMapping( "/{id}")
    public ResultModel<Boolean> delete( @PathVariable("id") Long id) {
        QueryWrapper<SysDeptEntity> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",id);
        boolean b = sysDeptService.removeById(id);
        if (!b){
            return ResultModel.valueOf(ResultCode.FAILURE, false);
        }
        return ResultModel.valueOf(ResultCode.SUCCESS, true);
    }
    @ApiOperation(value = "添加用户", notes = "添加用户")
    @ApiImplicitParam(name = "userParam", value = "参数", required = true, dataType = "UserParam")
    @PostMapping( "/addUser")
    public ResultModel<Boolean> insert( @RequestBody SysDeptEntity SysDeptEntity) {
        SysUserEntity sysUserEntity = DozerUtil.mapper(SysDeptEntity, SysUserEntity.class);
        boolean b = sysDeptService.save(SysDeptEntity);
        if (!b){
            return ResultModel.valueOf(ResultCode.FAILURE, false);
        }
        return ResultModel.valueOf(ResultCode.SUCCESS, true);
    }



}
