package com.whl.common.models;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * 
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2020-03-01 20:29:16
 */
@Data
@TableName("user")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer userId;
	/**
	 * 
	 */
	private String userUsername;
	/**
	 * 
	 */
	private String userPassword;
	/**
	 * 
	 */
	private String userNickname;
	/**
	 * 
	 */
	private String userPhone;
	/**
	 * 
	 */
	private String userEmail;

}
