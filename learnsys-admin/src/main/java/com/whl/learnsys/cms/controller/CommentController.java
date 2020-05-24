package com.whl.learnsys.cms.controller;

import com.whl.common.models.CommentEntity;
import com.whl.common.service.CommentService;
import com.whl.common.util.R;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 评论
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2020-04-27 19:30:28
 */
@RestController
@RequestMapping("sys/comment")
@CrossOrigin
@Api(tags = "评论管理")
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:comment:list")
    public R list(@RequestParam Map<String, Object> params) {
//        PageUtils page = commentService.(params);

        return R.ok().put("page", null);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:comment:info")
    public R info(@PathVariable("id") Long id) {
        CommentEntity comment = commentService.getById(id);

        return R.ok().put("comment", comment);
    }

    /**
     * 评论
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:comment:save")
    public R save(@RequestBody CommentEntity comment) {
        commentService.save(comment);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:comment:update")
    public R update(@RequestBody CommentEntity comment) {

        commentService.updateById(comment);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:comment:delete")
    public R delete(@RequestBody Long[] ids) {
        commentService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
