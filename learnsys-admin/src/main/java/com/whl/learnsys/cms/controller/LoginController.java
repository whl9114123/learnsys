package com.whl.learnsys.cms.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.whl.common.enums.ResultCode;
import com.whl.common.models.ResultModel;
import com.whl.common.param.UserParam;
import com.whl.common.service.CacheService;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Api(tags = {"login"})
@RestController
@RequestMapping("/")
public class LoginController {
    @Autowired
    DefaultKaptcha defaultKaptcha;
    @Autowired
    CacheService cacheService;


    @PostMapping("/login")
    public ResultModel<String> login(@RequestBody UserParam param) {
        try {
//            String gifcode = cacheService.getObject("gifcode", String.class);
//            if (!StringUtils.isBlank(gifcode) && gifcode.equals(param.getCode())) {
//               ResultModel.valueOf(ResultCode.false, null, "验证码校验失败");
//            }
            //密码加密 1.加密内容 2.盐值,3加密次数
//            String md5Hash=new Md5Hash(param.getPassword(),param.getUsername(),3).toString();
            UsernamePasswordToken upToken = new UsernamePasswordToken(param.getUsername(), param.getPassword());
            Subject subject = SecurityUtils.getSubject();
            subject.login(upToken);
            String sessionId = (String) subject.getSession().getId();
            return ResultModel.valueOf(ResultCode.SUCCESS, sessionId, "登录成功");
        } catch (Exception e) {
            return ResultModel.valueOf(ResultCode.SUCCESS, null, "登录失败");
        }
    }

    /**
     * 获取验证码
     * @param response
     */
    @GetMapping("/getCode")
    public void getGifCode(HttpServletResponse response) throws IOException {
        byte[] verByte = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            cacheService.setObjectMinutes("gifcode", createText, 10);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        verByte = jpegOutputStream.toByteArray();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = response.getOutputStream();
        responseOutputStream.write(verByte);
        responseOutputStream.flush();
        responseOutputStream.close();
    }
}
