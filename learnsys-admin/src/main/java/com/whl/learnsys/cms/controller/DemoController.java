package com.whl.learnsys.cms.controller;

import io.swagger.annotations.Api;
import net.xinhuamm.push.autoconfigure.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wuhuanling
 * demo测试框架搭建状态
 */
@Api(tags = {"demo"})
@RestController
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    private PersonService service;

    @PostMapping("/aa")
    public String aa() {
        return service.sayHello();
    }

}
