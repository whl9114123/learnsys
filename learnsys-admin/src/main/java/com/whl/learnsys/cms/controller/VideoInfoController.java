package com.whl.learnsys.cms.controller;

import com.whl.common.models.VideoInfoEntity;
import com.whl.common.service.VideoInfoService;
import com.whl.common.util.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;



/**
 * @author wuhuanling
 */
@Api(tags = "视频管理")
@RestController
@RequestMapping("sys/videoinfo")
public class VideoInfoController {
    @Autowired
    private VideoInfoService videoInfoService;

    /**
     * 列表
     */


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")

    public R info(@PathVariable("id") Long id){
        VideoInfoEntity videoInfo = videoInfoService.getById(id);

        return R.ok().put("videoInfo", videoInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")

    public R save(@RequestBody VideoInfoEntity videoInfo){
        videoInfoService.save(videoInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")

    public R update(@RequestBody VideoInfoEntity videoInfo){

        videoInfoService.updateById(videoInfo);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")

    public R delete(@RequestBody Long[] ids){
        videoInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
