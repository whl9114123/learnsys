package com.whl.learnsys.cms.controller;

import com.whl.common.models.ClazzEntity;
import com.whl.common.service.ClazzService;
import com.whl.common.util.R;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 班级
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2020-04-27 19:30:28
 */
@CrossOrigin
@RestController
@RequestMapping("sys/clazz")
@Api(tags = "班级管理")
public class ClazzController {
    @Autowired
    private ClazzService clazzService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:clazz:list")
    public R list(@RequestParam Map<String, Object> params) {


        return R.ok().put("page", 1);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:clazz:info")
    public R info(@PathVariable("id") Long id) {
        ClazzEntity clazz = clazzService.getById(id);

        return R.ok().put("clazz", clazz);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:clazz:save")
    public R save(@RequestBody ClazzEntity clazz) {
        clazzService.save(clazz);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:clazz:update")
    public R update(@RequestBody ClazzEntity clazz) {

        clazzService.updateById(clazz);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:clazz:delete")
    public R delete(@RequestBody Long[] ids) {
        clazzService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
