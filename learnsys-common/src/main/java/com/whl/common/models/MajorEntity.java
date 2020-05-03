package com.whl.common.models;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 专业
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2020-04-27 19:30:28
 */
@Data
@TableName("major")
public class MajorEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 专业id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 专业名称
     */
    private String name;
    /**
     * 描述
     */
    private Date remark;
    /**
     * 学院id
     */
    private Long deptId;
    /**
     * 创建时间
     */
    private Date createTime;

}
