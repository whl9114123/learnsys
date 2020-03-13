package com.whl.learnsys.cms.controller;

import com.whl.common.models.CourseInfoEntity;
import com.whl.common.service.CourseInfoService;
import com.whl.common.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;



/**
 * 
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2020-03-13 16:00:37
 */
@RestController
@RequestMapping("sys/courseinfo")
public class CourseInfoController {
    @Autowired
    private CourseInfoService courseInfoService;



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
