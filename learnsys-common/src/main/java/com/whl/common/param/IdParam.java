package com.whl.common.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author : xym
 * @date : 2020-03-10 11:33
 */
@Data

public class IdParam {

    private   Long id;
}
