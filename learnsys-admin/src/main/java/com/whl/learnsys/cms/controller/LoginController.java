package com.whl.learnsys.cms.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.whl.common.models.SysUserEntity;
import com.whl.common.param.UserParam;
import com.whl.common.service.CacheService;
import com.whl.common.util.R;
import com.whl.learnsys.cms.realm.ShiroUtils;
import io.swagger.annotations.Api;
import org.apache.shiro.authc.*;
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
@CrossOrigin
@RequestMapping("/")
public class LoginController {
    @Autowired
    DefaultKaptcha defaultKaptcha;
    @Autowired
    CacheService cacheService;

    @PostMapping("/login")
    public R login(@RequestBody UserParam param) {
        //redis 获取对象
//        String gifcode = cacheService.getObject("gifcode", String.class);
//        if (!StringUtils.isBlank(gifcode) && gifcode.equals(param.getCode())) {
//            ResultModel.valueOf(ResultCode.FAILURE, null, "验证码校验失败");
//        }
//        //密码加密 1.加密内容 2.盐值,3加密次数
//        String md5Hash = new Md5Hash(param.getPassword(), param.getUsername(), 3).toString();
        Long uid = 0L;
        try {
            Subject subject = ShiroUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(param.getUsername(), param.getPassword());
            subject.login(token);
            SysUserEntity user = (SysUserEntity) subject.getPrincipal();
            uid = user.getUserId();
        } catch (UnknownAccountException e) {
            return R.error(e.getMessage());
        } catch (IncorrectCredentialsException e) {
            return R.error("账号或密码不正确");
        } catch (LockedAccountException e) {
            return R.error("账号已被锁定,请联系管理员");
        } catch (AuthenticationException e) {
            return R.error("账户验证失败");
        }

        return R.ok().put("uid", uid);
    }


    /**
     * 获取验证码
     *
     * @param response
     */
    @GetMapping("/getCode")
    public void getGifCode(HttpServletResponse response) throws IOException {
        byte[] verByte = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
//            cacheService.setObjectMinutes("gifcode", createText, 10);
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
