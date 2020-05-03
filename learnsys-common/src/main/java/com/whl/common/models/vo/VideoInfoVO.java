package com.whl.common.models.vo;

import lombok.Data;

@Data
public class VideoInfoVO {
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
}
