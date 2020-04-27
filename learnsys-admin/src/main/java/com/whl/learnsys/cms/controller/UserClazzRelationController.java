package com.whl.learnsys.cms.controller;

import com.whl.common.models.UserClazzRelationEntity;
import com.whl.common.service.UserClazzRelationService;
import com.whl.common.util.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 用户班级关联表
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2020-04-27 19:30:28
 */
@RestController
@RequestMapping("sys/userclazzrelation")
@CrossOrigin
public class UserClazzRelationController {
    @Autowired
    private UserClazzRelationService userClazzRelationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:userclazzrelation:list")
    public R list(@RequestParam Map<String, Object> params) {
//        PageUtils page = userClazzRelationService.queryPage(params);
//
//        return R.ok().put("page", page);
        return null;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:userclazzrelation:info")
    public R info(@PathVariable("id") Long id) {
        UserClazzRelationEntity userClazzRelation = userClazzRelationService.getById(id);

        return R.ok().put("userClazzRelation", userClazzRelation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:userclazzrelation:save")
    public R save(@RequestBody UserClazzRelationEntity userClazzRelation) {
        userClazzRelationService.save(userClazzRelation);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:userclazzrelation:update")
    public R update(@RequestBody UserClazzRelationEntity userClazzRelation) {

        userClazzRelationService.updateById(userClazzRelation);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:userclazzrelation:delete")
    public R delete(@RequestBody Long[] ids) {
        userClazzRelationService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
