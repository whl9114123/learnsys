package com.whl.learnsys.cms.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Api(tags = {"上传视频模块"})
@RestController
@CrossOrigin
public class UploadController {

    private String  url;
    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @PostMapping("/upload")
    public String uploadFile(@ApiParam(value = "上传的文件",required = true) MultipartFile file, HttpServletRequest request) {
        System.out.print("上传文件==="+"\n");
        //判断文件是否为空
        if (file.isEmpty()) {
            return "上传文件不可为空";
        }
     // 获取文件名
        String fileName = file.getOriginalFilename();
//        System.out.print("上传的文件名为: "+fileName+"\n");
        fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
        System.out.print("（加个时间戳，尽量避免文件名称重复）保存的文件名为: "+fileName+"\n");
        //加个时间戳，尽量避免文件名称重复
        String path = uploadFolder+fileName;
        //String path = "E:/fileUpload/" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
        //文件绝对路径
        System.out.print("保存文件绝对路径"+path+"\n");
        //创建文件路径
        File dest = new File(path);

        //判断文件是否已经存在
        if (dest.exists()) {
            return "文件已经存在";
        }

        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }
        try {
            //上传文件
            file.transferTo(dest); //保存文件
            System.out.print("保存文件路径"+path+"\n");
            //url="http://你自己的域名/项目名/images/"+fileName;//正式项目
            url = "http://localhost:8888/static/" + fileName;//本地运行项目

            System.out.print("保存的完整url===="+url+"\n");

        } catch (IOException e) {
            log.error("",e);
            return "上传失败";
        }

        return "上传成功,文件url=="+url;
    }



}
