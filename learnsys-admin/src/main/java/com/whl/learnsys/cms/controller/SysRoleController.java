package com.whl.learnsys.cms.controller;

import com.whl.common.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * 角色
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2020-03-07 18:18:34
 */
@RestController
@RequestMapping("sys/sysrole")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;


}
