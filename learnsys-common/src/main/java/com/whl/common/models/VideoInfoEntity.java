package com.whl.common.models;

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
@TableName("video_info")
public class VideoInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 视频标题
	 */
	private String title;
	/**
	 * 视频大小
	 */
	private Double size;
	/**
	 * 视频时间
	 */
	private Long videoTime;
	/**
	 * 视频地址
	 */
	private String url;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Date modifyTime;
	/**
	 * 状态 1：启用 2：禁用 -1删除
	 */
	private Integer status;
	/**
	 * 审核状态 1：已通过 2：未通过 0：审核中
	 */
	private Integer approvalStatus;

}
