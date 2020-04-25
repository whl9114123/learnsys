package com.whl.learnsys.cms.controller;


import com.whl.common.enums.ResultCode;
import com.whl.common.models.ResultModel;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ErrorController {

    //公共错误跳转
    @RequestMapping(value = "autherror")
    public ResultModel<Boolean> autherror(int code) {

        return code == 1 ? ResultModel.valueOf(ResultCode.UNAUTHENTICATED, false) : ResultModel.valueOf(ResultCode.UNAUTHORISE, false);
    }

}
