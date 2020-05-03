package com.whl.common.models.vo;

import com.whl.common.models.SysUserEntity;
import lombok.Data;

import java.util.List;

@Data
public class CourseInfoVO {
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
    private SysUserEntity userEntity;

    private String typeName;
    private List<VideoInfoVO> videoInfoVOList;
}
