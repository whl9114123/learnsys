package com.whl.learnsys.cms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.whl.common.models.CourseInfoEntity;
import com.whl.common.models.SysUserEntity;
import com.whl.common.models.VideoInfoEntity;
import com.whl.common.models.vo.CourseInfoVO;
import com.whl.common.models.vo.VideoInfoVO;
import com.whl.common.service.CourseInfoService;
import com.whl.common.service.SysUserService;
import com.whl.common.service.VideoInfoService;
import com.whl.common.util.DozerUtil;
import com.whl.common.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author wuhuanling
 */
@Api(tags = "课程管理")
@RestController
@RequestMapping("sys/courseinfo")
public class CourseInfoController {
    @Autowired
    private CourseInfoService courseInfoService;
    @Autowired
    private VideoInfoService videoInfoService;
    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(value = "获取课程列表")
    @PostMapping("/list")
    public R list() {
        QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<>();
        List<CourseInfoEntity> list = courseInfoService.list();
        List<Long> collect = list.stream().map(c -> c.getTeacherId()).collect(Collectors.toList());
        List<SysUserEntity> user = sysUserService.list(queryWrapper.in("user_id", collect));
        HashMap<Long, String> map = new HashMap<>();
        user.forEach(c -> {
            map.put(c.getUserId(), c.getUsername());
        });
        list.forEach(c -> {
            if (c.getApprovalStatus() == 1) {
                c.setApproval("通过");
            } else {
                c.setApproval("不通过");
            }
            c.setTeacherName(map.get(c.getTeacherId()));
        });
        return R.ok().put("list", list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        CourseInfoEntity courseInfo = courseInfoService.getById(id);

        CourseInfoVO courseInfoVO = DozerUtil.mapper(courseInfo, CourseInfoVO.class);
        SysUserEntity teacher = sysUserService.getById(courseInfo.getTeacherId());
        courseInfoVO.setUserEntity(teacher);
        QueryWrapper<VideoInfoEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", id);
        List<VideoInfoEntity> list = videoInfoService.list(queryWrapper);
        if (!CollectionUtils.isEmpty(list)) {
            List<VideoInfoVO> videoInfoVOList = DozerUtil.mapList(list, VideoInfoVO.class);
            courseInfoVO.setVideoInfoVOList(videoInfoVOList);
        }
        return R.ok().put("courseInfo", courseInfoVO);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")

    public R save(@RequestBody CourseInfoEntity courseInfo) {
        courseInfoService.save(courseInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")

    public R update(@RequestBody CourseInfoEntity courseInfo) {

        courseInfoService.updateById(courseInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")

    public R delete(@RequestBody Long[] ids) {
        courseInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
