package com.whl.learnsys;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConfig {

    @Autowired
    MybatisPlusProperties mybatisPlusProperties;
    @Autowired
    SqlSessionFactory sqlSessionFactory;

//
//    @Bean
//    MybatisMapperRefresh getRefresh(){
//        MybatisMapperRefresh mybatisMapperRefresh = new MybatisMapperRefresh(mybatisPlusProperties.resolveMapperLocations(),sqlSessionFactory,true);
//        return mybatisMapperRefresh;
//    }
}
