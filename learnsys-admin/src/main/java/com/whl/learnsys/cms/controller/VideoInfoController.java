package com.whl.learnsys.cms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.whl.common.enums.ResultCode;
import com.whl.common.models.ResultModel;
import com.whl.common.models.VideoInfoEntity;
import com.whl.common.param.PageParam;
import com.whl.common.service.VideoInfoService;
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
@Api(tags = "视频管理")
@RestController
@RequestMapping("sys/videoinfo")
public class VideoInfoController {
    @Autowired
    private VideoInfoService videoInfoService;

    /**
     * 列表
     */

    @ApiOperation(value = "获取用户列表", notes = "获取用户列表")
    @ApiImplicitParam(name = "pageParam", value = "获取参数", required = true, dataType = "PageParam")
    @PostMapping( "/list")
    public ResultModel<Page<VideoInfoEntity>> list(@RequestBody PageParam pageParam) {

        Page<VideoInfoEntity> page = new Page<>(pageParam.getCurrent(), pageParam.getSize());
        Page<VideoInfoEntity> page1 = videoInfoService.page(page);
        int hasMore = 0;
        if (page1.getPages() >= pageParam.getCurrent()) {
            hasMore = 1;
        }


        return ResultModel.valueOf(ResultCode.FAILURE,page1,null,hasMore);
    }

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
