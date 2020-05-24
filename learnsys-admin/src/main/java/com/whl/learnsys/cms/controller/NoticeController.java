package com.whl.learnsys.cms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.whl.common.models.NoticeEntity;
import com.whl.common.service.NoticeService;
import com.whl.common.util.R;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


/**
 * 公告
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2020-04-27 19:30:28
 */
@RestController
@RequestMapping("sys/notice")
@CrossOrigin
@Api(tags = "公告管理")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    /**
     * 列表
     */
    @RequestMapping("/list/{id}")

    public R list(@PathVariable("id") long id) {

        List<NoticeEntity> list = noticeService.list(new QueryWrapper<NoticeEntity>().eq("user_id", id));

//        PageUtils page = noticeService.queryPage(params);
//


        return R.ok().put("list", list);

    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:notice:info")
    public R info(@PathVariable("id") Long id) {
        NoticeEntity notice = noticeService.getById(id);

        return R.ok().put("notice", notice);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:notice:save")
    public R save(@RequestBody NoticeEntity notice) {
        noticeService.save(notice);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:notice:update")
    public R update(@RequestBody NoticeEntity notice) {

        noticeService.updateById(notice);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:notice:delete")
    public R delete(@RequestBody Long[] ids) {
        noticeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
