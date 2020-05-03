package com.whl.common.models;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2020-04-27 19:30:28
 */
@Data
@TableName("comment")
public class CommentEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 课程id
     */
    private Long courseId;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;

}
