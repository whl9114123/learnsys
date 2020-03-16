package com.whl.common.models


		;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2020-03-13 16:00:37
 */
@Data
@TableName("course_type")
public class CourseTypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private String name;

	/**
	 * 
	 */
	private Integer status;
	/**
	 * 
	 */
	private Date creatTime;
	/**
	 * 
	 */
	private Date modifyTime;

}
