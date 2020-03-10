package com.whl.learnsys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wuhuanling
 */
@SpringBootApplication(scanBasePackages = "com.whl.*")
@MapperScan("com.whl.common.mappers")
public class LearnsysAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnsysAdminApplication.class, args);
        System.out.println("------------------------SpringApplication启动--------------------");
    }

}
