package com.whl.learnsys.cms.controller;

import com.whl.common.models.CourseTypeEntity;
import com.whl.common.service.CourseTypeService;
import com.whl.common.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;



/**
 *
 */
@RestController
@RequestMapping("sys/coursetype")
public class CourseTypeController {
    @Autowired
    private CourseTypeService courseTypeService;

    /**
     * 列表
     */


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")

    public R info(@PathVariable("id") Long id){
        CourseTypeEntity courseType = courseTypeService.getById(id);

        return R.ok().put("courseType", courseType);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")

    public R save(@RequestBody CourseTypeEntity courseType){
        courseTypeService.save(courseType);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")

    public R update(@RequestBody CourseTypeEntity courseType){

        courseTypeService.updateById(courseType);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")

    public R delete(@RequestBody Long[] ids){
        courseTypeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
