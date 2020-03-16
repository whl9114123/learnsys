package com.whl.common.param;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author whl
 */
@Data
@Accessors(chain = true)
public class CourseRequest extends PageParam {
    private Long typeId;
}
