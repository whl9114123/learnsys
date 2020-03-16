package com.whl.learnsys.cms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.whl.common.enums.ResultCode;
import com.whl.common.models.ResultModel;
import com.whl.common.models.SysUserEntity;
import com.whl.common.param.PageParam;
import com.whl.common.param.UserParam;
import com.whl.common.service.CacheService;
import com.whl.common.service.SysUserService;
import com.whl.common.util.DozerUtil;
import com.whl.common.util.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@Api(tags = {"用户管理模块"})
@RestController
@RequestMapping("sys/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private CacheService cacheService;

    @ApiOperation(value = "获取用户列表", notes = "获取用户列表")
    @ApiImplicitParam(name = "pageParam", value = "获取参数", required = true, dataType = "PageParam")
    @PostMapping( "/list")
    public ResultModel<Page<SysUserEntity>> list(@RequestBody PageParam pageParam) {

        Page<SysUserEntity> page = new Page<>(pageParam.getCurrent(), pageParam.getSize());
        Page<SysUserEntity> page1 = sysUserService.page(page);
        int hasMore = 0;
        if (page1.getPages() >= pageParam.getCurrent()) {
            hasMore = 1;
        }


        return ResultModel.valueOf(ResultCode.FAILURE,page1,null,hasMore);
    }

    @ApiOperation(value = "获取用户", notes = "获取用户")
    @ApiImplicitParam(name = "id", value = " 参数", required = true, dataType = "Long")

    @GetMapping("/{id}")
    public ResultModel<SysUserEntity> getUser(@PathVariable("id") Long id) {
        String key = "user:" + id;
        SysUserEntity user = cacheService.getObject(key, SysUserEntity.class);
        if (ObjectUtils.isEmpty(user)) {
            user = sysUserService.getById(id);
            cacheService.setObject(key, user);
        }




        return ResultModel.valueOf(ResultCode.SUCCESS,user);
    }

    @ApiOperation(value = "删除用户", notes = "获取用户")
    @ApiImplicitParam(name = "id", value = " 参数", required = true, dataType = "Long")
    @DeleteMapping("/{id}")
    public ResultModel<Boolean> deleteUser(@PathVariable("id") Long id) {
        QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", id);
        boolean b = sysUserService.removeById(id);
        if (!b) {
            return ResultModel.valueOf(ResultCode.FAILURE, false);
        }
        return ResultModel.valueOf(ResultCode.SUCCESS, true);
    }
    @ApiOperation(value = "添加用户", notes = "添加用户")
    @ApiImplicitParam(name = "userParam", value = "参数", required = true, dataType = "UserParam")
    @PostMapping("/addUser")
    public ResultModel<Boolean> insertUser(@RequestBody UserParam userParam) {
        SysUserEntity sysUserEntity = DozerUtil.mapper(userParam, SysUserEntity.class);
        boolean b = sysUserService.save(sysUserEntity);
        if (!b) {
            return ResultModel.valueOf(ResultCode.FAILURE, false);
        }
        return ResultModel.valueOf(ResultCode.SUCCESS, true);
    }
    @ApiOperation(value = "添加用户", notes = "添加用户")
    @ApiImplicitParam(name = "userParam", value = "参数", required = true, dataType = "UserParam")
    @PostMapping("/login")
    public ResultModel<String> login(@RequestBody UserParam userParam) {
        SysUserEntity sysUserEntity = DozerUtil.mapper(userParam, SysUserEntity.class);
        QueryWrapper<SysUserEntity> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",userParam.getUsername()).eq("password",userParam.getPassword());
        SysUserEntity user = sysUserService.getOne(queryWrapper);
        if (ObjectUtils.isEmpty(user)){
            return ResultModel.valueOf(ResultCode.FAILURE,null);
        }
     Map<String,Object> map=  new HashMap<>();
        map.put("user",user);
        String jwt = JwtUtils.createJwt(map);
        return ResultModel.valueOf(ResultCode.SUCCESS, jwt);
    }

    @PostMapping( "/lUser")
    public ResultModel<Boolean> lo( @RequestBody UserParam param) {
        try {
            //密码加密 1.加密内容 2.盐值,3加密次数
            String md5Hash=new Md5Hash(param.getPassword(),param.getUsername(),3).toString();
            UsernamePasswordToken upToken = new UsernamePasswordToken(param.getUsername(), param.getPassword());
            Subject subject = SecurityUtils.getSubject();
            subject.login(upToken);
        } catch (Exception e) {

        }
return null;
    }



}
