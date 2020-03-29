package com.whl.learnsys.cms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.whl.common.enums.ResultCode;
import com.whl.common.models.ResultModel;
import com.whl.common.models.SysRoleEntity;
import com.whl.common.models.SysUserEntity;
import com.whl.common.param.PageParam;
import com.whl.common.param.UserParam;
import com.whl.common.service.SysRoleService;
import com.whl.common.util.DozerUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
/**
 * @author wuhuanling
 */

@Api(tags = {"角色管理模块"})
@RestController
@RequestMapping("sys/role")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;


    @ApiOperation(value = "获取角色列表")
    @PostMapping( "/list")
    public ResultModel<Page<SysRoleEntity>> list(@RequestBody PageParam pageParam) {

        Page<SysRoleEntity> page=new Page<>(pageParam.getCurrent(),pageParam.getSize());
        Page<SysRoleEntity> page1 =sysRoleService.page(page);
        int hasMore=0;
        if(page1.getPages()>=pageParam.getCurrent()) {
            hasMore=1;
        }


        return ResultModel.valueOf(ResultCode.FAILURE,page1,null,hasMore);
    }

    @ApiOperation(value = "获取角色详情")

    @GetMapping( "/{id}")
    public ResultModel<SysRoleEntity> detail( @PathVariable("id") Long id) {

        SysRoleEntity sysRoleEntity = sysRoleService.getById(id);


        return ResultModel.valueOf(ResultCode.SUCCESS,sysRoleEntity);
    }

    @ApiOperation(value = "删除角色", notes = "删除角色")
    @DeleteMapping( "/{id}")
    public ResultModel<Boolean> delete( @PathVariable("id") Long id) {
        QueryWrapper<SysUserEntity> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",id);
        boolean b = sysRoleService.removeById(id);
        if (!b){
            return ResultModel.valueOf(ResultCode.FAILURE, false);
        }
        return ResultModel.valueOf(ResultCode.SUCCESS, true);
    }
    @ApiOperation(value = "添加角色")
    @ApiImplicitParam(name = "userParam", value = "参数", required = true, dataType = "UserParam")
    @PostMapping( "/addUser")
    public ResultModel<Boolean> insert( @RequestBody SysRoleEntity sysRoleEntity) {
        SysRoleEntity sysRoleEntity1 = DozerUtil.mapper(sysRoleEntity, SysRoleEntity.class);
        boolean b = sysRoleService.save(sysRoleEntity1);
        if (!b){
            return ResultModel.valueOf(ResultCode.FAILURE, false);
        }
        return ResultModel.valueOf(ResultCode.SUCCESS, true);
    }



}
