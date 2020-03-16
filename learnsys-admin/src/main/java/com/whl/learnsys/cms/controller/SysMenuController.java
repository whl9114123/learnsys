package com.whl.learnsys.cms.controller;

import com.whl.common.service.SysMenuService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




/**
 * @author wuhuanling
 */
@Api(tags = "权限管理")
@RestController
@RequestMapping("sys/menu")
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;


}
