package com.whl.common.param;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author : xym
 * @date : 2020-03-10 13:02
 */
@Data
public class UserParam {
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 验证码
     */
    private String code;


}
