package com.whl.learnsys.cms.controller;

import com.whl.common.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * 部门管理
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2020-03-07 18:19:07
 */
@RestController
@RequestMapping("sys/sysdept")
public class SysDeptController {
    @Autowired
    private SysDeptService sysDeptService;



}
