package com.whl.learnsys.cms.controller;

import com.whl.common.models.MajorEntity;
import com.whl.common.service.MajorService;
import com.whl.common.util.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 专业
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2020-04-27 19:30:28
 */
@RestController
@RequestMapping("sys/major")
@CrossOrigin
public class MajorController {
    @Autowired
    private MajorService majorService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:major:list")
    public R list(@RequestParam Map<String, Object> params) {
//        PageUtils page = majorService.queryPage(params);
//
        return R.ok().put("page", null);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:major:info")
    public R info(@PathVariable("id") Long id) {
        MajorEntity major = majorService.getById(id);

        return R.ok().put("major", major);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:major:save")
    public R save(@RequestBody MajorEntity major) {
        majorService.save(major);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:major:update")
    public R update(@RequestBody MajorEntity major) {

        majorService.updateById(major);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:major:delete")
    public R delete(@RequestBody Long[] ids) {
        majorService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
