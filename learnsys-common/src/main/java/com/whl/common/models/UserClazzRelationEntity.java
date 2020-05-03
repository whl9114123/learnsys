package com.whl.common.models;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户班级关联表
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2020-04-27 19:30:28
 */
@Data
@TableName("user_clazz_relation")
public class UserClazzRelationEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 班级id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 班级名称
     */
    private String name;
    /**
     * 年级
     */
    private Integer year;
    /**
     * 专业id
     */
    private Long majorId;
    /**
     * 创建时间
     */
    private Date createTime;

}
