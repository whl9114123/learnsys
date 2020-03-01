package com.whl.learnsys.cms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.whl.common.enums.ResultCode;
import com.whl.common.models.ResultModel;
import com.whl.common.models.UserEntity;
import com.whl.common.param.PageParam;
import com.whl.common.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * 
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2020-03-01 20:29:16
 */
@Api(tags = {"用户模块"})

@RestController
@RequestMapping("sys/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 列表
     */
    @PostMapping(value = "/list")
    public ResultModel<Page<UserEntity>> list(@RequestBody PageParam pageParam) {

        Page<UserEntity> page=new Page<>(pageParam.getCurrent(),pageParam.getSize());
        Page<UserEntity> page1 =userService.page(page);
        int hasMore=0;
       if(page1.getPages()>=pageParam.getCurrent()) {
            hasMore=1;
       }


        return ResultModel.valueOf(ResultCode.FAILURE,page1,null,hasMore);
    }

}
