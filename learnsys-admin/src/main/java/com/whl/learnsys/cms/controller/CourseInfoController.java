package com.whl.learnsys.cms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.whl.common.enums.ResultCode;
import com.whl.common.models.CourseInfoEntity;
import com.whl.common.models.ResultModel;
import com.whl.common.param.CourseRequest;
import com.whl.common.service.CourseInfoService;
import com.whl.common.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;



/**
 * @author wuhuanling
 */
@Api(tags = "课程管理")
@RestController
@RequestMapping("sys/courseinfo")
public class CourseInfoController {
    @Autowired
    private CourseInfoService courseInfoService;




    @ApiOperation(value = "获取用户列表", notes = "获取用户列表")
    @ApiImplicitParam(name = "pageParam", value = "获取参数", required = true, dataType = "PageParam")
    @PostMapping( "/list")
    public ResultModel<Page<CourseInfoEntity>> list(@RequestBody CourseRequest param) {

        Page<CourseInfoEntity> page=new Page<>(param.getCurrent(),param.getSize());
        QueryWrapper<CourseInfoEntity> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("type_id",param.getTypeId());
        Page<CourseInfoEntity> page1 =courseInfoService.page(page,queryWrapper);
        int hasMore=0;
        if(page1.getPages()>=param.getCurrent()) {
            hasMore=1;
        }


        return ResultModel.valueOf(ResultCode.SUCCESS,page1,null,hasMore);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")

    public R info(@PathVariable("id") Long id){
        CourseInfoEntity courseInfo = courseInfoService.getById(id);

        return R.ok().put("courseInfo", courseInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")

    public R save(@RequestBody CourseInfoEntity courseInfo){
        courseInfoService.save(courseInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")

    public R update(@RequestBody CourseInfoEntity courseInfo){

        courseInfoService.updateById(courseInfo);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")

    public R delete(@RequestBody Long[] ids){
        courseInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
