//package com.whl.learnsys.cms.config.swagger;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebMvcConfig implements WebMvcConfigurer {
//    @Value("${file.staticAccessPath}")
//    private String staticAccessPath;
//
//    @Value("${smas.captrue.image.path}")
//    private String captureImagePath;
//
//    @Value("${file.uploadFolder}")
//    private String uploadFolder;
//
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**").allowedOrigins("*").allowCredentials(true).allowedMethods("*").maxAge(3600);
//    }
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler(staticAccessPath).addResourceLocations("file:" + uploadFolder + captureImagePath);
//    }
//
//}
