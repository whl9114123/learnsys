package com.whl.common.models;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2020-03-13 16:00:37
 */
@Data
@TableName("course_info")
public class CourseInfoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Long id;
    /**
     * 课程名字
     */
    private String title;
    /**
     * 描述
     */
    private String brief;
    /**
     * 授课老师
     */
    private String teacherId;
    /**
     * 审核状态
     */
    private Integer approvalStatus;
    /**
     * 状态
     */
    private Integer status;
    private Long typeId;
}
