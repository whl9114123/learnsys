package com.whl.learnsys.cms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.whl.common.enums.ResultCode;
import com.whl.common.models.ResultModel;
import com.whl.common.models.SysMenuEntity;
import com.whl.common.models.SysRoleEntity;
import com.whl.common.models.SysUserEntity;
import com.whl.common.param.PageParam;
import com.whl.common.service.SysMenuService;
import com.whl.common.util.DozerUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author wuhuanling
 */
@Api(tags = "权限管理")
@RestController
@RequestMapping("sys/menu")
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;


    @ApiOperation(value = "获取角色列表")
    @PostMapping( "/list")
    public ResultModel<Page<SysMenuEntity>> list(@RequestBody PageParam pageParam) {

        Page<SysMenuEntity> page=new Page<>(pageParam.getCurrent(),pageParam.getSize());
        Page<SysMenuEntity> page1 =sysMenuService.page(page);
        int hasMore=0;
        if(page1.getPages()>=pageParam.getCurrent()) {
            hasMore=1;
        }


        return ResultModel.valueOf(ResultCode.FAILURE,page1,null,hasMore);
    }

    @ApiOperation(value = "获取角色详情")

    @GetMapping( "/{id}")
    public ResultModel<SysMenuEntity> detail( @PathVariable("id") Long id) {

        SysMenuEntity sysMenuEntity = sysMenuService.getById(id);


        return ResultModel.valueOf(ResultCode.SUCCESS,sysMenuEntity);
    }

    @ApiOperation(value = "删除角色", notes = "删除角色")
    @DeleteMapping( "/{id}")
    public ResultModel<Boolean> delete( @PathVariable("id") Long id) {
        QueryWrapper<SysMenuEntity> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",id);
        boolean b = sysMenuService.removeById(id);
        if (!b){
            return ResultModel.valueOf(ResultCode.FAILURE, false);
        }
        return ResultModel.valueOf(ResultCode.SUCCESS, true);
    }
    @ApiOperation(value = "添加角色")
    @ApiImplicitParam(name = "userParam", value = "参数", required = true, dataType = "UserParam")
    @PostMapping( "/addUser")
    public ResultModel<Boolean> insert( @RequestBody SysMenuEntity sysMenuEntity) {
        SysMenuEntity sysRoleEntity1 = DozerUtil.mapper(sysMenuEntity, SysMenuEntity.class);
        boolean b = sysMenuService.save(sysRoleEntity1);
        if (!b){
            return ResultModel.valueOf(ResultCode.FAILURE, false);
        }
        return ResultModel.valueOf(ResultCode.SUCCESS, true);
    }


}
